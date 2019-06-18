package com.example.standartsolutionever;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;

public class Choping extends Fragment {
    Boolean index;
    ListView lvData;
    Button btncarsave;
    Button btncartrans;
    String slccarrier;
    private OnDataTransfer25 mListener;


    public static Choping newInstance() {

        Bundle args = new Bundle();

        Choping fragment = new Choping();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        index = false;
        View rootview = inflater.inflate(R.layout.fragment_choping, container, false);
        final String[] Carriers = {" Колка Да", " Колка Нет"};
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
                mListener.onDataTransfer25(slccarrier);
                if(!isEmpty(slccarrier)) btncartrans.setEnabled(!index);
                else Toast.makeText(getActivity(),"Сделайте выбор",Toast.LENGTH_LONG).show();
            }
        });

        // устанавливаем для списка адаптер
        btncartrans = rootview.findViewById(R.id.btncardtrans);
        btncartrans.setEnabled(index);
        btncartrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).openNewContentFragment ( Crushing.newInstance());
            }
        });



        return rootview;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDataTransfer25) {
            mListener = (OnDataTransfer25) context;
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

    public interface OnDataTransfer25 {
        // TODO: Update argument type and name
        void onDataTransfer25(String string);
    }

}
