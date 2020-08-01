package com.example.standartsolutionever;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.standartsolutionever.entites.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A fragment representing a list of Items.
 */
public class MyCustomListView extends Fragment  {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 4;
    Cursor cursor;
    private List<Order> orders= new ArrayList<>();


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MyCustomListView() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MyCustomListView newInstance(int columnCount) {
        MyCustomListView fragment = new MyCustomListView();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    private Cursor returnLastDayData () {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd ", Locale.getDefault());

        Date date = new Date();
        DBHelper mDBhelper = DBHelper.getInstance(getContext());
        SQLiteDatabase db = mDBhelper.getWritableDatabase();
        String sqlQueryForCheck = " SELECT " +
                RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME + " AS provider " +
                RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME + " AS kind_of_raw_materials " +
                RwmUtilityContract.TypeOfRwmEntry.COLUMN_NAME + " AS type_of_raw_materials " +
                RwmUtilityContract.OrdersEntry.COLUMN_QUATITY + " AS quantity " +
                RwmUtilityContract.OrdersEntry.COLUMN_CARRIER + " AS carrier " +
                " FROM "
                + RwmUtilityContract.OrdersEntry.TABLE_NAME +
                " JOIN " +
                RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME +
                " ON " +
                RwmUtilityContract.OrdersEntry.COLUMN_PROVIDERS_ID + "= " + RwmUtilityContract.ProvidersOfRwmEntry._ID +
                " JOIN" +
                RwmUtilityContract.KindOfRwmEntry.TABLE_NAME +
                " ON " +
                RwmUtilityContract.OrdersEntry.COLUMN_KINDRWM_ID + "=" + RwmUtilityContract.KindOfRwmEntry._ID +
                " JOIN " +
                RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME +
                " ON " +
                RwmUtilityContract.OrdersEntry.COLUMN_TYPERWM_ID + "= " + RwmUtilityContract.TypeOfRwmEntry._ID +
                " WHERE " + RwmUtilityContract.OrdersEntry.COLUMN_DATE.substring(0, 9) + " =?";


         cursor = db.rawQuery(sqlQueryForCheck, new String[]{dateFormat.format(date)});
        return cursor;
    }
    private List<Order>  getListOrderFromCursor(Cursor cursor) {
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            Order order =new Order();
            order.setProvider(cursor.getString(cursor.getColumnIndex("provider")));
            order.setKindRwm(cursor.getString(cursor.getColumnIndex("kind_of_raw_materials")));
            order.setTypeRwm(cursor.getString(cursor.getColumnIndex("type_of_raw_materials")));
            order.setCarrier(cursor.getString(cursor.getColumnIndex("carrier")));
            order.setQuantity(cursor.getDouble(cursor.getColumnIndex("quantity")));
            orders.add(order);

        }
        return orders;
    }












    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycustom_listview_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            Cursor cursorNew=returnLastDayData ();
            orders=getListOrderFromCursor(cursorNew);
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(orders));
        }
        return view;
    }
}