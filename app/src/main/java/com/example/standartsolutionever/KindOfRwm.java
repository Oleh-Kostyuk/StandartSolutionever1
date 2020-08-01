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


/**
 * A simple {@link Fragment} subclass.
 */
public class KindOfRwm extends BackStackFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    Boolean index;
    TextView infTextView;
    ListView lvData;
    Button btnkindtrans;
    Button btnkindsave;
    String slckindRwm;
    SimpleCursorAdapter scAdapter;
    private static final int LOADER_ID = 225;
    private OnDataTransfer3 mListener;
   /* public KindofRwm() {
        // Required empty public constructor
    } */
 public static KindOfRwm newInstance() {

     Bundle args = new Bundle();

     KindOfRwm fragment = new KindOfRwm();
     fragment.setArguments(args);
     return fragment;
 }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        index = false;
        View rootview = inflater.inflate(R.layout.fragment_kindof_rwm, container, false);
       //final String[] KindofRwm = {"Тырса", "Опилки", "Горбыль", "Дрова","Солома"};
        infTextView =rootview.findViewById(R.id.infTextView);
        infTextView.setTextSize(20);
        infTextView.setText(Html.fromHtml("<b><font color=\"red\">фрагмент: " +
                " </font>выбор вида сырья </b>"
                + "<i> 1 2 3 </i>"
                + "<font color=\"red\"><b>4 </b></font>"
                + "<i>5 6 </i>"));
        String[] from = new String[] {RwmUtilityContract.KindOfRwmEntry.COLUMN_NAME };
        int[] to = new int[] { android.R.id.text1 };
        lvData = rootview.findViewById(R.id.KindOfRwm);
        scAdapter = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_single_choice, null, from,to );
        getLoaderManager().initLoader(LOADER_ID, null, this);
        lvData.setAdapter(scAdapter);



        btnkindsave = rootview.findViewById(R.id.btnkindsave);
        btnkindsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDataTransfer3(slckindRwm);
                if(!isEmpty(slckindRwm)) btnkindtrans.setEnabled(!index);
                else Toast.makeText(getActivity(),"Выбрать вид сырья",Toast.LENGTH_LONG).show();
            }
        });

        // устанавливаем для списка адаптер
        lvData.setAdapter(scAdapter);
        btnkindtrans = rootview.findViewById(R.id.btnkindtrans);
        btnkindtrans.setEnabled(index);
        btnkindtrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.root_frame1, new Quantityfrag());
                transaction.addToBackStack(null);
                transaction.commit(); */
                ((MainActivity) getActivity()).openNewContentFragment ( Quantityfrag.newInstance());
            }
        });


        return rootview;
    }
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof KindOfRwm.OnDataTransfer3) {
                mListener = (OnDataTransfer3) context;
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
        };
        if(id == LOADER_ID)
            return new CursorLoader(getActivity(), Uri.withAppendedPath(CONTENT_AUTHORITY_URI,
                    RwmUtilityContract.KindOfRwmEntry.TABLE_NAME),
                    projection,
                    null,
                    null,
                    RwmUtilityContract.KindOfRwmEntry._ID);
        else
            throw new InvalidParameterException("Invalid loader id");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader,final Cursor cursor) {
        scAdapter.swapCursor(cursor);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                slckindRwm = cursor.getString(1);
            }
        });
        btnkindsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDataTransfer3(slckindRwm);
                if (!isEmpty(slckindRwm)) btnkindtrans.setEnabled(!index);
                else Toast.makeText(getActivity(), "Выберите тип материала !!!", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    public interface OnDataTransfer3 {
            // TODO: Update argument type and name
            void onDataTransfer3(String string);
        }
    }


