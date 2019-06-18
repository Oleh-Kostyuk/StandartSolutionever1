package com.example.standartsolutionever;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.example.standartsolutionever.RwmContract.CONTENT_AUTHORITY_URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mainfragment extends Fragment {
    public static Fragment frag;
    FragmentManager fm;
    TextView provmain;
    TextView carmain;
    TextView typemain;
    TextView kindmain;
    TextView qtmain;
    Button btnmainsave;
    Button btncansel;

   /* public Mainfragment() {
        // Required empty public constructor
    } */

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
                    ContentValues  values = new ContentValues();
                    values.put (RwmContract.OrdersEntry.COLUMN_PROVIDERS,provmain.getText().toString());
                    values.put (RwmContract.OrdersEntry.COLUMN_TYPERWM,typemain.getText().toString());
                    values.put (RwmContract.OrdersEntry.COLUMN_KINDRWM, kindmain.getText().toString());
                    values.put (RwmContract.OrdersEntry.COLUMN_QUATITY,qtmain.getText().toString());
                    values.put (RwmContract.OrdersEntry.COLUMN_CARRIER,carmain.getText().toString());
                     Uri uri =Uri.withAppendedPath (CONTENT_AUTHORITY_URI,RwmContract.OrdersEntry.TABLE_NAME);
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
               /* FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.root_frame1, new Tab1());
                transaction.addToBackStack(null);
                transaction.commit(); */
               frag =Tab1.newInstance();
                ((MainActivity) getActivity()).openNewContentFragment ( frag);


            }
        });

        return rootview;
    }
    public void SetProvmain (String string){
        provmain.setText( string);
    }
}
