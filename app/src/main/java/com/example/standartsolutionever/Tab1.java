package com.example.standartsolutionever;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.security.InvalidParameterException;

import static android.text.TextUtils.isEmpty;
import static com.example.standartsolutionever.RwmContract.CONTENT_AUTHORITY_URI;
import static com.example.standartsolutionever.RwmContract.CONTENT_AUTHORITY_URI;


public class Tab1 extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ListView lvData;
    Button btnproviders ;
    boolean index;
    Button btnproviders2;
    String slcProviders;
    SimpleCursorAdapter scAdapter;

    private static final int LOADER_ID = 225;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private   OnDataTransfer1 mListener;


    // TODO: Rename and change types and number of parameters
    public static Tab1 newInstance() {
        Tab1 fragment = new Tab1();
      /*  Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args); */
        return fragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        index = false;
        View rootview = inflater.inflate(R.layout.fragment_tab1, container, false);
        lvData = rootview.findViewById(R.id.providers);
        btnproviders =rootview.findViewById(R.id.btnproviders);
        String[] from = new String[] {RwmContract.ProvidersOfRwmEntry.COLUMN_NAME };
        int[] to = new int[] { android.R.id.text1 };

        scAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_single_choice, null, from,to );
        lvData.setAdapter(scAdapter);
        getLoaderManager().initLoader(LOADER_ID, null, this);
        btnproviders2 =rootview.findViewById(R.id.btnproviders2);

        btnproviders = rootview.findViewById(R.id.btnproviders);
        btnproviders.setEnabled(index);
        btnproviders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.root_frame1, new Carrier());
                transaction.addToBackStack(null);
                transaction.commit(); */
                ((MainActivity) getActivity()).openNewContentFragment ( Carrier.newInstance());
            }
        });

        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDataTransfer1) {
            mListener = (OnDataTransfer1) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle bundle) {
        String[] projection = {
                RwmContract.ProvidersOfRwmEntry._ID,
                RwmContract.ProvidersOfRwmEntry.COLUMN_NAME,
        };
        if(id == LOADER_ID)
            return new CursorLoader(getActivity(), Uri.withAppendedPath(CONTENT_AUTHORITY_URI,
                    RwmContract.ProvidersOfRwmEntry.TABLE_NAME),
                    projection,
                    null,
                    null,
                    RwmContract.ProvidersOfRwmEntry._ID);
        else
            throw new InvalidParameterException("Invalid loader id");
    }


    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, final Cursor cursor) {
        scAdapter.swapCursor(cursor);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                 slcProviders = cursor.getString(1);
            }
        });
        btnproviders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mListener.onDataTransfer1(slcProviders);
            if (!isEmpty(slcProviders)) btnproviders.setEnabled(!index);
            else Toast.makeText(getActivity(), "Выберите поставщика",Toast.LENGTH_LONG).show();



            }
        });
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
    interface OnDataTransfer1 {
         void  onDataTransfer1 ( String string);
    }


    }


