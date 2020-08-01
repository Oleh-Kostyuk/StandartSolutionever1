package com.example.standartsolutionever;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.net.Uri;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidParameterException;

import static android.text.TextUtils.isEmpty;
import static com.example.standartsolutionever.RwmUtilityContract.CONTENT_AUTHORITY_URI;


public class Tab2 extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    TextView infTextView;
    ListView lvData;
    Button btnproviders ;
    Button btnproviders2;
    boolean index;

    SimpleCursorAdapter scAdapter;
    String slcMaterialsForRefinary;

    private static final int LOADER_ID = 225;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private   OnDataTransfer21 mListener;


    // TODO: Rename and change types and number of parameters
    public static Tab2 newInstance() {
        Tab2 fragment = new Tab2();
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
        View rootview = inflater.inflate(R.layout.fragment_tab2, container, false);
        infTextView =rootview.findViewById(R.id.infTextView);
        infTextView.setTextSize(20);
        infTextView.setText(Html.fromHtml("<b><font color=\"red\">фрагмент: " +
                " </font>выбор вида сырья </b>"
                + "<font color=\"red\"><b>1 </b></font>"
                + "<i> 2 3 4 5  </i>"));
        lvData = rootview.findViewById(R.id.providers);
        btnproviders =rootview.findViewById(R.id.btnproviders);
        String[] from = new String[] {RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME };
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

                ((MainActivity) getActivity()).openNewContentFragment ( QuantityforRefinary.newInstance());
            }
        });

        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDataTransfer21) {
            mListener = (OnDataTransfer21) context;
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
                RwmUtilityContract.KindOfRwmEntry._ID,
                RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME,
                RwmUtilityContract.KindOfRwmEntry.MAY_PROCESSING
        };
        String selection1 = RwmUtilityContract.KindOfRwmEntry.MAY_PROCESSING  + " =? ";
        if(id == LOADER_ID)
            return new CursorLoader(getActivity(), Uri.withAppendedPath(CONTENT_AUTHORITY_URI,
                    RwmUtilityContract.KindOfRwmEntry.TABLE_NAME),
                    projection,
                    selection1,
                    new String[]{Integer.toString(1)},
                    RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME);
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
             slcMaterialsForRefinary = cursor.getString(1);
            }
        });
        btnproviders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDataTransfer21(slcMaterialsForRefinary);
                if (!isEmpty(slcMaterialsForRefinary)) btnproviders.setEnabled(!index);
                else Toast.makeText(getActivity(), "Выберите сырье",Toast.LENGTH_LONG).show();



            }
        });
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
    interface OnDataTransfer21 {
        void  onDataTransfer21 ( String string);
    }


}


