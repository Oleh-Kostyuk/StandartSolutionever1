package com.example.standartsolutionever;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import static com.example.standartsolutionever.RwmContract.CONTENT_AUTHORITY_URI;


public class RwmProvider extends ContentProvider {
    com.example.standartsolutionever.DBHelper mDBhelper;
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

        matcher.addURI(RwmContract.CONTENT_AUTHORITY,RwmContract.ProvidersOfRwmEntry.TABLE_NAME,PROVIDERS);
        matcher.addURI(RwmContract.CONTENT_AUTHORITY,RwmContract.KindOfRwmEntry.TABLE_NAME,KINDOFRAWMATERIALS);
        matcher.addURI(RwmContract.CONTENT_AUTHORITY,RwmContract.KindOfRwmEntry.TABLE_NAME +"/#",KINDOFRAWMATERIALS_ID);
        matcher.addURI(RwmContract.CONTENT_AUTHORITY,RwmContract.TypeOfRwmEntry.TABLE_NAME,TYPEOFRAWMATERIALS);
        matcher.addURI(RwmContract.CONTENT_AUTHORITY,RwmContract.OrdersEntry.TABLE_NAME,ORDERS);
        matcher.addURI(RwmContract.CONTENT_AUTHORITY,RwmContract.OrdersEntry.TABLE_NAME + "/#",ORDERS_ID);
        matcher.addURI(RwmContract.CONTENT_AUTHORITY,RwmContract.RefinaryEntry.TABLE_NAME,REFINARY);
        matcher.addURI(RwmContract.CONTENT_AUTHORITY,RwmContract.RefinaryEntry.TABLE_NAME + "/#",REFINARY_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDBhelper = DBHelper.getInstance(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int match = sUriMatcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch(match) {
            case PROVIDERS:
                queryBuilder.setTables(RwmContract.ProvidersOfRwmEntry.TABLE_NAME);
                break;
            case KINDOFRAWMATERIALS:
                queryBuilder.setTables(RwmContract.KindOfRwmEntry.TABLE_NAME);
                break;
            case KINDOFRAWMATERIALS_ID:
                queryBuilder.setTables(RwmContract.KindOfRwmEntry.TABLE_NAME);
                long id3 = ContentUris.parseId(uri);
                queryBuilder.appendWhere(RwmContract.KindOfRwmEntry._ID + "="+ id3);
                break;
            case TYPEOFRAWMATERIALS:
                queryBuilder.setTables(RwmContract.TypeOfRwmEntry.TABLE_NAME);
                break;
            case ORDERS:
                queryBuilder.setTables(RwmContract.OrdersEntry.TABLE_NAME);
                break;
            case ORDERS_ID:
                queryBuilder.setTables(RwmContract.OrdersEntry.TABLE_NAME);
                long id1 = ContentUris.parseId(uri);
                queryBuilder.appendWhere(RwmContract.OrdersEntry._ID + " = " + id1);
                break;
            case REFINARY:
                queryBuilder.setTables(RwmContract.RefinaryEntry.TABLE_NAME);
                break;
            case REFINARY_ID:
                queryBuilder.setTables(RwmContract.RefinaryEntry.TABLE_NAME);
                long id2 = ContentUris.parseId(uri);
                queryBuilder.appendWhere(RwmContract.RefinaryEntry._ID + " = " + id2);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        SQLiteDatabase db = mDBhelper.getReadableDatabase();
        return queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
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

                String orders =RwmContract.OrdersEntry.TABLE_NAME;
                Id = db.insert(orders, null, values);
                if(Id > 0){
                    returnUri = ContentUris.withAppendedId(uri,Id);
                }
                else{
                    throw new android.database.SQLException("Failed to insert: " + uri.toString());}
                break;
            case REFINARY:
                db = mDBhelper.getWritableDatabase();
                Id = db.insert(RwmContract.RefinaryEntry.TABLE_NAME, null, values);
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
                return db.delete(RwmContract.ProvidersOfRwmEntry.TABLE_NAME, selection, selectionArgs);
            case ORDERS:
                return db.delete(RwmContract.OrdersEntry.TABLE_NAME, selection, selectionArgs);
            case ORDERS_ID:
                long taskId1 = ContentUris.parseId(uri);
                selectionCriteria = RwmContract.OrdersEntry._ID + " = " + taskId1;
                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                return db.delete(RwmContract.OrdersEntry.TABLE_NAME, selectionCriteria, selectionArgs);
            case REFINARY:
                return db.delete(RwmContract.RefinaryEntry.TABLE_NAME, selection, selectionArgs);
            case REFINARY_ID:
                long taskId2 = ContentUris.parseId(uri);
                selectionCriteria = RwmContract.OrdersEntry._ID + " = " + taskId2;
                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                return db.delete(RwmContract.OrdersEntry.TABLE_NAME, selectionCriteria, selectionArgs);
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
                return db.update(RwmContract.ProvidersOfRwmEntry.TABLE_NAME,values, selection, selectionArgs);
            case ORDERS:
                return db.update(RwmContract.OrdersEntry.TABLE_NAME,values,selection, selectionArgs);
            case ORDERS_ID:
                long taskId1 = ContentUris.parseId(uri);
                selectionCriteria = RwmContract.OrdersEntry._ID + " = " + taskId1;
                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                return db.update(RwmContract.OrdersEntry.TABLE_NAME,values, selectionCriteria, selectionArgs);
            case REFINARY:
                return db.update(RwmContract.RefinaryEntry.TABLE_NAME,values, selection, selectionArgs);
            case REFINARY_ID:
                long taskId2 = ContentUris.parseId(uri);
                selectionCriteria = RwmContract.OrdersEntry._ID + " = " + taskId2;
                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                return db.update(RwmContract.OrdersEntry.TABLE_NAME,values, selectionCriteria, selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown URI: "+ uri);
        }
    }}

