package com.example.standartsolutionever;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.jumpmind.symmetric.android.SQLiteOpenHelperRegistry;
import org.jumpmind.symmetric.android.SymmetricService;
import org.jumpmind.symmetric.common.ParameterConstants;

import java.util.Properties;

import static com.example.standartsolutionever.RwmUtilityContract.DATABASE_NAME;


public class RwmContentProvider extends ContentProvider {

    com.example.standartsolutionever.DBHelper mDBhelper;
    private final String REGISTRATION_URL = "http://192.168.1.103:31415/sync/server-000";
    private final String NODE_ID = "android-001";
    private final String NODE_GROUP = "store";

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    public static final int PROVIDERS = 100;
    public static final int KINDOFRAWMATERIALS = 200;
    public static final int KINDOFRAWMATERIALS_ID =201;
    public static final int TYPEOFRAWMATERIALS = 300;
    public static final int ORDERS = 400;
    public static final int ORDERS_ID = 401;
    public static final int REFINARY=500;
    public static final int REFINARY_ID = 501;

    private static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(RwmUtilityContract.CONTENT_AUTHORITY, RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME,PROVIDERS);
        matcher.addURI(RwmUtilityContract.CONTENT_AUTHORITY, RwmUtilityContract.KindOfRwmEntry.TABLE_NAME,KINDOFRAWMATERIALS);
        matcher.addURI(RwmUtilityContract.CONTENT_AUTHORITY, RwmUtilityContract.KindOfRwmEntry.TABLE_NAME +"/#",KINDOFRAWMATERIALS_ID);
        matcher.addURI(RwmUtilityContract.CONTENT_AUTHORITY, RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME,TYPEOFRAWMATERIALS);
        matcher.addURI(RwmUtilityContract.CONTENT_AUTHORITY, RwmUtilityContract.OrdersEntry.TABLE_NAME,ORDERS);
        matcher.addURI(RwmUtilityContract.CONTENT_AUTHORITY, RwmUtilityContract.OrdersEntry.TABLE_NAME + "/#",ORDERS_ID);
        matcher.addURI(RwmUtilityContract.CONTENT_AUTHORITY, RwmUtilityContract.RefinaryEntry.TABLE_NAME,REFINARY);
        matcher.addURI(RwmUtilityContract.CONTENT_AUTHORITY, RwmUtilityContract.RefinaryEntry.TABLE_NAME + "/#",REFINARY_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDBhelper = DBHelper.getInstance(getContext());

        SQLiteOpenHelperRegistry.register(DATABASE_NAME, mDBhelper);

        Intent intent = new Intent(getContext(), SymmetricService.class);

        intent.putExtra(SymmetricService.INTENTKEY_SQLITEOPENHELPER_REGISTRY_KEY, DATABASE_NAME);
        intent.putExtra(SymmetricService.INTENTKEY_REGISTRATION_URL, REGISTRATION_URL);
        intent.putExtra(SymmetricService.INTENTKEY_EXTERNAL_ID, NODE_ID);
        intent.putExtra(SymmetricService.INTENTKEY_NODE_GROUP_ID, NODE_GROUP);
        intent.putExtra(SymmetricService.INTENTKEY_START_IN_BACKGROUND, true);


        Properties properties = new Properties();
        properties.put(ParameterConstants.FILE_SYNC_ENABLE, "true");
        properties.put("start.file.sync.tracker.job", "true");
        properties.put("start.file.sync.push.job", "true");
        properties.put("start.file.sync.pull.job", "true");
        properties.put("job.file.sync.pull.period.time.ms", "10000");

        intent.putExtra(SymmetricService.INTENTKEY_PROPERTIES, properties);

        getContext().startService(intent);

        // Assumes that any failures will be reported by a thrown exception.
        return true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int match = sUriMatcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch(match) {
            case PROVIDERS:
                queryBuilder.setTables(RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME);
                break;
            case KINDOFRAWMATERIALS:
                queryBuilder.setTables(RwmUtilityContract.KindOfRwmEntry.TABLE_NAME);
                break;
            case KINDOFRAWMATERIALS_ID:
                queryBuilder.setTables(RwmUtilityContract.KindOfRwmEntry.TABLE_NAME);
                long id3 = ContentUris.parseId(uri);
                queryBuilder.appendWhere(RwmUtilityContract.KindOfRwmEntry._ID + "="+ id3);
                break;
            case TYPEOFRAWMATERIALS:
                queryBuilder.setTables(RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME);
                break;
            case ORDERS:
                queryBuilder.setTables(RwmUtilityContract.OrdersEntry.TABLE_NAME);
                break;
            case ORDERS_ID:
                queryBuilder.setTables(RwmUtilityContract.OrdersEntry.TABLE_NAME);
                long id1 = ContentUris.parseId(uri);
                queryBuilder.appendWhere(RwmUtilityContract.OrdersEntry._ID + " = " + id1);
                break;
            case REFINARY:
                queryBuilder.setTables(RwmUtilityContract.RefinaryEntry.TABLE_NAME);
                break;
            case REFINARY_ID:
                queryBuilder.setTables(RwmUtilityContract.RefinaryEntry.TABLE_NAME);
                long id2 = ContentUris.parseId(uri);
                queryBuilder.appendWhere(RwmUtilityContract.RefinaryEntry._ID + " = " + id2);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        SQLiteDatabase db = mDBhelper.getReadableDatabase();
        Cursor cursor;
        cursor= queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    @Nullable
    @Override
    public String getType(@NonNull  Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db;
        Uri returnUri;
        long Id;
        final int match = sUriMatcher.match(uri);
        switch(match){
            case ORDERS:
                db = mDBhelper.getWritableDatabase();

                String orders = RwmUtilityContract.OrdersEntry.TABLE_NAME;
                Id = db.insert(orders, null, values);
                if(Id > 0){
                    returnUri = ContentUris.withAppendedId(uri,Id);
                }
                else{
                    throw new android.database.SQLException("Failed to insert: " + uri.toString());}
                break;
            case REFINARY:
                db = mDBhelper.getWritableDatabase();
                Id = db.insert(RwmUtilityContract.RefinaryEntry.TABLE_NAME, null, values);
                if(Id > 0){
                    returnUri = ContentUris.withAppendedId(uri,Id);
                }
                else{
                    throw new android.database.SQLException("Failed to insert: " + uri.toString());
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: "+ uri);
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        final SQLiteDatabase db = mDBhelper.getWritableDatabase();
        String selectionCriteria = selection;
        switch(match){
            case PROVIDERS:
                return db.delete(RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME, selection, selectionArgs);
            case ORDERS:
                return db.delete(RwmUtilityContract.OrdersEntry.TABLE_NAME, selection, selectionArgs);
            case ORDERS_ID:
                long taskId1 = ContentUris.parseId(uri);
                selectionCriteria = RwmUtilityContract.OrdersEntry._ID + " = " + taskId1;
                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                return db.delete(RwmUtilityContract.OrdersEntry.TABLE_NAME, selectionCriteria, selectionArgs);
            case REFINARY:
                return db.delete(RwmUtilityContract.RefinaryEntry.TABLE_NAME, selection, selectionArgs);
            case REFINARY_ID:
                long taskId2 = ContentUris.parseId(uri);
                selectionCriteria = RwmUtilityContract.OrdersEntry._ID + " = " + taskId2;
                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                return db.delete(RwmUtilityContract.OrdersEntry.TABLE_NAME, selectionCriteria, selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown URI: "+ uri);
        }}

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        final SQLiteDatabase db = mDBhelper.getWritableDatabase();
        String selectionCriteria = selection;
        switch(match){
            case PROVIDERS:
                return db.update(RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME,values, selection, selectionArgs);
            case ORDERS:
                return db.update(RwmUtilityContract.OrdersEntry.TABLE_NAME,values,selection, selectionArgs);
            case ORDERS_ID:
                long taskId1 = ContentUris.parseId(uri);
                selectionCriteria = RwmUtilityContract.OrdersEntry._ID + " = " + taskId1;
                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                return db.update(RwmUtilityContract.OrdersEntry.TABLE_NAME,values, selectionCriteria, selectionArgs);
            case REFINARY:
                return db.update(RwmUtilityContract.RefinaryEntry.TABLE_NAME,values, selection, selectionArgs);
            case REFINARY_ID:
                long taskId2 = ContentUris.parseId(uri);
                selectionCriteria = RwmUtilityContract.OrdersEntry._ID + " = " + taskId2;
                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                return db.update(RwmUtilityContract.OrdersEntry.TABLE_NAME,values, selectionCriteria, selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown URI: "+ uri);
        }
    }}

