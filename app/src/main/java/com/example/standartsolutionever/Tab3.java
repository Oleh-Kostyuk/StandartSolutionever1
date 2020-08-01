package com.example.standartsolutionever;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.standartsolutionever.entites.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Tab3 extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    String provider;
    TextView infTextView;
    RecyclerView recyclerView;
    MyItemRecyclerViewAdapter adapter;
    Button btnInput;
    Button btnRefinary;
    boolean index;
    int rowCount;
    Cursor cursor;
    List<Order> orders= new ArrayList<>();
    private static final int LOADER_ID = 225;

    public static Tab3 newInstance() {
        Tab3 fragment = new Tab3();
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_tab3, container, false);
        infTextView = rootview.findViewById(R.id.infTextView);
        infTextView.setTextSize(20);
        infTextView.setText(Html.fromHtml("<b><font color=\"red\"> фрагмент:  </font>Данные за день </b>"
                + "<font color=\"red\"><b>1</b></font>"));

        recyclerView = rootview.findViewById(R.id.list);
        btnInput = rootview.findViewById(R.id.btninput);

        cursor = returnLastDayData();
        orders = getListOrderFromCursor(cursor);
        adapter = new MyItemRecyclerViewAdapter(orders);
        recyclerView.setAdapter( adapter);
//        lvData.setAdapter(scAdapter);
//        getLoaderManager().initLoader(LOADER_ID, null, this);
        btnRefinary = rootview.findViewById(R.id.btnrefinary);



        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootview;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    private Cursor returnLastDayData() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd ", Locale.getDefault());

        Date date = new Date();
        DBHelper mDBhelper = DBHelper.getInstance(getContext());
        SQLiteDatabase db = mDBhelper.getWritableDatabase();
        String sqlQueryForCheck = " SELECT " +
//                RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME +"."+
                "providers." +
                RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME + " AS provider,  " +
//                RwmUtilityContract.KindOfRwmEntry.TABLE_NAME +"."+
                "k."+
                RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME + " AS kind_of_raw_materials,  " +
//                RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME +"."+
                "type."+
                RwmUtilityContract.TypeOfRwmEntry.COLUMN_NAME + " AS type_of_raw_materials,  " +
                RwmUtilityContract.OrdersEntry.TABLE_NAME +"." +
                RwmUtilityContract.OrdersEntry.COLUMN_QUATITY + " AS quantity,  " +
                RwmUtilityContract.OrdersEntry.TABLE_NAME +"." +
                RwmUtilityContract.OrdersEntry.COLUMN_CARRIER + " AS carrier  " +
                " FROM "
                + RwmUtilityContract.OrdersEntry.TABLE_NAME + " AS orders " +
                " JOIN  " +
                RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME +" AS providers  "+
                " ON " +
               "orders." +RwmUtilityContract.OrdersEntry.COLUMN_PROVIDERS_ID + "= "
                + "providers." + RwmUtilityContract.ProvidersOfRwmEntry._ID +
                " JOIN " +
                RwmUtilityContract.KindOfRwmEntry.TABLE_NAME +" AS k" +
                " ON " +
                "orders."+RwmUtilityContract.OrdersEntry.COLUMN_KINDRWM_ID + "= "
                +"k."+ RwmUtilityContract.KindOfRwmEntry._ID +
                " JOIN " +
                RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME +" AS type  " +
                " ON " +
               "orders."+ RwmUtilityContract.OrdersEntry.COLUMN_TYPERWM_ID + "= " + "type."+RwmUtilityContract.TypeOfRwmEntry._ID;


        cursor = db.rawQuery(sqlQueryForCheck, null);

        if (cursor.getCount()!=0){
        rowCount=cursor.getCount();
            System.out.println(rowCount);}
         else   System.out.println("whats up?");
        return cursor;
    }

    private List<Order> getListOrderFromCursor(Cursor cursor) {
       if( cursor.moveToFirst())
      do{
            Order order = new Order();
            provider=cursor.getString(cursor.getColumnIndex("provider"));
            order.setProvider(cursor.getString(cursor.getColumnIndex("provider")));
            order.setKindRwm(cursor.getString(cursor.getColumnIndex("kind_of_raw_materials")));
            order.setTypeRwm(cursor.getString(cursor.getColumnIndex("type_of_raw_materials")));
            order.setCarrier(cursor.getString(cursor.getColumnIndex("carrier")));
            order.setQuantity(cursor.getDouble(cursor.getColumnIndex("quantity")));
            orders.add(order);
        ;
      }while  (cursor.moveToNext());
      else System.out.println("Нет значений");


        return orders;
    }
}
