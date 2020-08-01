package com.example.standartsolutionever;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cutting extends Fragment {
    TextView infTextView;
    Boolean index;
    ListView lvData;
    Button btncarsave;
    Button btncartrans;
    String slccarrier;
    private OnDataTransfer23 mListener;

    /* public Carrier () {

     };
 */
    public static Cutting newInstance() {

        Bundle args = new Bundle();

        Cutting fragment = new Cutting();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        index = false;
        View rootview = inflater.inflate(R.layout.fragment_cutting, container, false);
        String [] isCutting =null;
        if(MainActivity.slcMaterialsForRefinary.equals("Щепа"))
            isCutting = new String[]{"Нет порезки"};
        else isCutting = new String[]{"Была порезка", "Нет порезки"};
        final String[] Carriers = isCutting;
        infTextView =rootview.findViewById(R.id.infTextView);
        infTextView.setTextSize(20);
        infTextView.setText(Html.fromHtml("<b><font color=\"red\">фрагмент: " +
                " </font> порезка </b>"
                + "<i> 1 2 </i>"
                + "<font color=\"red\"><b>3 </b></font>"
                + "<i>4 5</i>"
        ));
        lvData = rootview.findViewById(R.id.Carrier);
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_single_choice, Carriers);
        lvData.setAdapter(adapter);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                slccarrier = Carriers[position];
            }
        });
        btncarsave = rootview.findViewById(R.id.btncarsave);
        btncarsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDataTransfer23(slccarrier);
                if(!isEmpty(slccarrier)) btncartrans.setEnabled(!index);
                else Toast.makeText(getActivity(),"Выбрать опцию",Toast.LENGTH_LONG).show();
            }
        });

        // устанавливаем для списка адаптер
        btncartrans = rootview.findViewById(R.id.btncardtrans);
        btncartrans.setEnabled(index);
        btncartrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).openNewContentFragment ( Counterbefore.newInstance());
            }
        });



        return rootview;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDataTransfer23) {
            mListener = (OnDataTransfer23) context;
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

    public interface OnDataTransfer23 {
        // TODO: Update argument type and name
        void onDataTransfer23(String string);
    }

}