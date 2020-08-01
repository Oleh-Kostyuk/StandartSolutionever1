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

;




public class Type_ofRwm extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{

    TextView infTextView;

    ListView lvData;
    String slctyperwm;

    Button btntype;
    Button btntype2;
    Boolean index;

    SimpleCursorAdapter scAdapter;
    private static final int LOADER_ID = 225;

    private ChoseTypeRwm  mListener;

    public static Type_ofRwm newInstance() {

    Bundle args = new Bundle();

    Type_ofRwm fragment = new Type_ofRwm();
    fragment.setArguments(args);
    return fragment;
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        index = false;
        View rootview = inflater.inflate(R.layout.fragment_type_ofrwm, container, false);
        infTextView =rootview.findViewById(R.id.infTextView);
        infTextView.setTextSize(20);
        infTextView.setText(Html.fromHtml("<b><font color=\"red\"> фрагмент: " +
                " </font>выбор типа древесины </b>"
                + "<i> 1 2 </i>"
                + "<font color=\"red\"><b>3 </b></font>"
                + "<i> 4 5 6 </i>"));

        lvData = rootview.findViewById(R.id.TypeOfRwm);
        String[] from = new String[] {RwmUtilityContract.TypeOfRwmEntry.COLUMN_NAME };
        int[] to = new int[] { android.R.id.text1 };


        scAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_single_choice, null, from,to );


        lvData.setAdapter(scAdapter);
        getLoaderManager().initLoader(LOADER_ID, null, this);
        lvData.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        btntype =rootview.findViewById(R.id.btntype);
        btntype.setEnabled(index);
        btntype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).openNewContentFragment ( KindOfRwm.newInstance());


            }

        });
        btntype2 = rootview.findViewById(R.id.btntype2);
        btntype2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mListener.choseTypeRwm(slctyperwm);
               if (!isEmpty(slctyperwm)) btntype.setEnabled(!index);
               else Toast.makeText(getActivity(),"Выбрать тип сырья !!!",Toast.LENGTH_LONG).show();
            }
        });
        return  rootview;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChoseTypeRwm) {
            mListener = (ChoseTypeRwm) context;
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
                RwmUtilityContract.TypeOfRwmEntry._ID,
                RwmUtilityContract.TypeOfRwmEntry.COLUMN_NAME,
        };
        if(id == LOADER_ID)
            return new CursorLoader(getActivity(), Uri.withAppendedPath(CONTENT_AUTHORITY_URI,
                    RwmUtilityContract.TypeOfRwmEntry.TABLE_NAME),
                    projection,
                    null,
                    null,
                    RwmUtilityContract.TypeOfRwmEntry._ID);
        else
            throw new InvalidParameterException("Invalid loader id");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, final Cursor cursor) {
        scAdapter.swapCursor(cursor);
        scAdapter.swapCursor(cursor);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                slctyperwm = cursor.getString(1);
            }

        });
        btntype2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.choseTypeRwm(slctyperwm);
                if (!isEmpty(slctyperwm)) btntype.setEnabled(!index);
                else Toast.makeText(getActivity(), "Выберите сорт древесины !!!",Toast.LENGTH_LONG).show();



            }
        });
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    public interface ChoseTypeRwm {

        void choseTypeRwm(String string);
    }
}