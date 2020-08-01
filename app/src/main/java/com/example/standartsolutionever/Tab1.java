package com.example.standartsolutionever;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidParameterException;

import static android.text.TextUtils.isEmpty;
import static com.example.standartsolutionever.RwmUtilityContract.CONTENT_AUTHORITY_URI;


public class Tab1 extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    TextView infTextView;
    ListView lvData;

    Button btnsave;
    Button btnturn;
    boolean index;

    SimpleCursorAdapter scAdapter;
    private static final int LOADER_ID = 225;


    private   ChoseProvider mListener;
    String slcProvider;

    public static Tab1 newInstance() {
        Tab1 fragment = new Tab1();
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
        infTextView =rootview.findViewById(R.id.choiceOfSupplier);
        infTextView.setTextSize(20);
        infTextView.setText(Html.fromHtml("<b><font color=\"red\"> фрагмент:  </font>выбор поставщика </b>"
                + "<font color=\"red\"><b>1 </b></font>"
                + "<i>2 3 4 5 6 </i>"));

        lvData = rootview.findViewById(R.id.providers);
        btnsave =rootview.findViewById(R.id.btnproviders);

        String[] from = new String[] {RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME };
        int[] to = new int[] { android.R.id.text1 };

        scAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_single_choice, null, from,to );
        scAdapter.notifyDataSetChanged();
        lvData.setAdapter(scAdapter);
        getLoaderManager().initLoader(LOADER_ID, null, this);
        btnturn =rootview.findViewById(R.id.btnproviders2);

        btnsave = rootview.findViewById(R.id.btnproviders);
        btnsave.setEnabled(index);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openNewContentFragment ( Carrier.newInstance());
            }
        });

        return rootview;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChoseProvider) {
            mListener = (ChoseProvider) context;
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
                RwmUtilityContract.ProvidersOfRwmEntry._ID,
                RwmUtilityContract.ProvidersOfRwmEntry.COLUMN_NAME,
        };
        if(id == LOADER_ID)
            return new CursorLoader(getActivity(), Uri.withAppendedPath(CONTENT_AUTHORITY_URI,
                    RwmUtilityContract.ProvidersOfRwmEntry.TABLE_NAME),
                    projection,
                    null,
                    null,
                    RwmUtilityContract.ProvidersOfRwmEntry._ID);
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
                 slcProvider = cursor.getString(1);
            }
        });
        btnturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mListener.ChoseProvider(slcProvider);
            if (!isEmpty(slcProvider)) btnsave.setEnabled(!index);
            else Toast.makeText(getActivity(), "Выберите поставщика",Toast.LENGTH_LONG).show();



            }
        });
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
    interface ChoseProvider {
         void  ChoseProvider ( String string);
    }

    }


