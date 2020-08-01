package com.example.standartsolutionever;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;


/**
 * A simple {@link Fragment} subclass.
 */
public class Quantityfrag extends Fragment {
    Boolean index;
    TextView infTextView;
    EditText quantity;
    Button btnqttrans;
    Button btnqtsave;
    String Quantity;
    private OnDataTransfer4 mListener;

   /* public Quantityfrag() {
        // Required empty public constructor
    } */
public static  Quantityfrag newInstance() {

    Bundle args = new Bundle();

    Quantityfrag fragment = new Quantityfrag();
    fragment.setArguments(args);
    return fragment;
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        index=false;
        View rootview = inflater.inflate(R.layout.fragment_quantityfrag, container, false);
        infTextView =rootview.findViewById(R.id.infTextView);
        infTextView.setTextSize(20);
        infTextView.setText(Html.fromHtml("<b><font color=\"red\">фрагмент: " +
                " </font>количество сырья </b>"
                + "<i> 1 2 3 4  </i>"
                + "<font color=\"red\"><b>5 </b></font>"
                + "<i>6 </i>"));
        quantity  = rootview.findViewById(R.id.quantity);
        btnqtsave = rootview.findViewById(R.id.btnqtsave);
        btnqtsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity = quantity.getText().toString();
                mListener.onDataTransfer4(Quantity);
                if (!isEmpty(Quantity))btnqttrans.setEnabled(!index);
                else Toast.makeText(getActivity(),"Укажите количество",Toast.LENGTH_LONG).show();
            }});
        btnqttrans =rootview.findViewById(R.id.btnqttrans);
        btnqttrans.setEnabled(index);
        btnqttrans .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).openNewContentFragment ( Mainfragment.newInstance());
                }

                });


        return rootview;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDataTransfer4) {
            mListener = (OnDataTransfer4) context;
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

    public interface OnDataTransfer4 {
        // TODO: Update argument type and name
        void onDataTransfer4(String quantity);
    }
}
