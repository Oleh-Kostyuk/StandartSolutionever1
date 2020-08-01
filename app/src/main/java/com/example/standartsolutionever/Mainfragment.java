package com.example.standartsolutionever;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.standartsolutionever.RwmUtilityContract.CONTENT_AUTHORITY_URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mainfragment extends Fragment {
    public static Fragment frag;
    DBHelper mDBhelper;
    FragmentManager fm;
    TextView infTextView;
    TextView provmain;
    TextView carmain;
    TextView typemain;
    TextView kindmain;
    TextView qtmain;
    Button btnmainsave;
    Button btncansel;

    public static Mainfragment newInstance() {

        Bundle args = new Bundle();

        Mainfragment fragment = new Mainfragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View rootview = inflater.inflate(R.layout.fragment_mainfragment, container, false);
        infTextView =rootview.findViewById(R.id.infTextView);
        infTextView.setTextSize(20);
        infTextView.setText(Html.fromHtml("<b><font color=\"red\"> Конечный фрагмент: " +
                " </font>количество сырья </b>"
                + "<i> 1 2 3 4 5 </i>"
                + "<font color=\"red\"><b>6 </b></font>"));
            provmain =rootview.findViewById(R.id.provmain);
            provmain.setText(MainActivity.slcproviders);
            carmain = rootview.findViewById(R.id.carmain);
            carmain.setText(MainActivity.Carrier);
            typemain = rootview.findViewById(R.id.typemain);
            typemain.setText(MainActivity.slctyperwm);
            kindmain = rootview.findViewById(R.id.kindmain);
            kindmain.setText(MainActivity.slckindrwm);
            qtmain = rootview.findViewById(R.id.qtmain);
            qtmain.setText(MainActivity.Quantity);
            btnmainsave = rootview.findViewById(R.id.btnsave);

            btnmainsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ContentResolver contentResolver = getContext().getContentResolver ();
                    ContentValues values = new ContentValues();
                    mDBhelper = DBHelper.getInstance(getContext());
                    SQLiteDatabase db = mDBhelper.getReadableDatabase();
//                    Cursor cursor =db.rawQuery("select  * from "+ RwmContract.ProvidersOfRwmEntry.TABLE_NAME +
//                             " where " + RwmContract.ProvidersOfRwmEntry.COLUMN_NAME +" =?",
//                             new String[]{MainActivity.slcproviders});
//                   Integer idprov =  cursor.getInt(0);
//                     Uri uri1 =Uri.withAppendedPath (CONTENT_AUTHORITY_URI, RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME) ;
//                     String[] mprojection = {RwmUtilityContract.ProvidersOfRwmEntry._ID};
//                     Cursor cursor = contentResolver.query(uri1,mprojection,
//                             RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME +
//                            " =?",new String[]{MainActivity.slcproviders}, null) ;
                    Cursor cursor= db.rawQuery("select * from " + RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME +
                                    " where " + RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME +" =?",
                            new String[]{MainActivity.slcproviders});
                      cursor.moveToFirst();
                     Integer idprov =  cursor.getInt(0) ;
                    values.put (RwmUtilityContract.OrdersEntry.COLUMN_PROVIDERS_ID,idprov);

                    cursor = db.rawQuery("select * from " + RwmUtilityContract.KindOfRwmEntry.TABLE_NAME +
                             " where " + RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME +" =?",
                            new String[]{MainActivity.slckindrwm});
                    cursor.moveToFirst() ;
                    Integer idkindRwm= cursor.getInt(0);
                    values.put (RwmUtilityContract.OrdersEntry.COLUMN_KINDRWM_ID,idkindRwm);

                    cursor = db.rawQuery("select * from " + RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME +
                                    " where " + RwmUtilityContract.TypeOfRwmEntry.COLUMN_NAME +" =?",
                            new String[]{MainActivity.slctyperwm}) ;

                      cursor.moveToFirst();

                    Integer idtypeRwm =cursor.getInt(0);
                    values.put (RwmUtilityContract.OrdersEntry.COLUMN_TYPERWM_ID,idtypeRwm);
                    values.put (RwmUtilityContract.OrdersEntry.COLUMN_COST,0);
                    values.put (RwmUtilityContract.OrdersEntry.COLUMN_QUATITY,qtmain.getText().toString());
                    values.put (RwmUtilityContract.OrdersEntry.COLUMN_CARRIER,carmain.getText().toString());
                    values.put (RwmUtilityContract.OrdersEntry.COLUMN_DATE,getDateTime());
                     Uri uri =Uri.withAppendedPath (CONTENT_AUTHORITY_URI, RwmUtilityContract.OrdersEntry.TABLE_NAME);
                    contentResolver.insert(uri,values);
                    MainActivity.slcproviders = "";
                    MainActivity.slctyperwm = "";
                    MainActivity.slckindrwm = "";
                    MainActivity.Quantity = "";
                    MainActivity.Carrier = "";
                    provmain.setText("");
                    typemain.setText ("");
                    kindmain.setText ("");
                    qtmain.setText ("");
                    carmain.setText("");
                    btnmainsave.setEnabled(false);
                    fm = getFragmentManager();
                    if(fm.getBackStackEntryCount()>0){
                        fm.popBackStackImmediate(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }
            }
            });
        btncansel = rootview.findViewById(R.id.btncansel);
        btncansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.slcproviders = "";
                MainActivity.slctyperwm = "";
                MainActivity.slckindrwm = "";
                MainActivity.Quantity = "";
                provmain.setText("");
                typemain.setText ("");
                kindmain.setText ("");
                qtmain.setText ("");

               frag =Tab1.newInstance();
                ((MainActivity) getActivity()).openNewContentFragment ( frag);


            }
        });

        return rootview;
    }
    public void SetProvmain (String string){
        provmain.setText( string);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
