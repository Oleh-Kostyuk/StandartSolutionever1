package com.example.standartsolutionever;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;


/**
 * A simple {@link Fragment} subclass.
 */
public class KindofRwm extends BackStackFragmn {
    Boolean index;
    ListView lvData;
    Button btnkindtrans;
    Button btnkindsave;
    String slckindRwm;
    private OnDataTransfer3 mListener;
   /* public KindofRwm() {
        // Required empty public constructor
    } */
 public static  KindofRwm newInstance() {

     Bundle args = new Bundle();

     KindofRwm fragment = new KindofRwm();
     fragment.setArguments(args);
     return fragment;
 }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        index = false;
        View rootview = inflater.inflate(R.layout.fragment_kindof_rwm, container, false);
       final String[] KindofRwm = {"Тырса", "Опилки", "Горбыль", "Дрова","Солома"};
        lvData = rootview.findViewById(R.id.KindOfRwm);
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_single_choice, KindofRwm);
        lvData.setAdapter(adapter);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                slckindRwm = KindofRwm[position];
            }
        });
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
        lvData.setAdapter(adapter);
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
            if (context instanceof KindofRwm.OnDataTransfer3) {
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

        public interface OnDataTransfer3 {
            // TODO: Update argument type and name
            void onDataTransfer3(String string);
        }
    }


