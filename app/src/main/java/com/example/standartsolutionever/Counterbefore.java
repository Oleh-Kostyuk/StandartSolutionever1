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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class Counterbefore extends Fragment {
    Boolean index;
    Boolean requestmatcher;
    TextView infTextView;
    EditText quantity;
    Button btnqttrans;
    Button btnqtsave;
    String Quantity;
    private Pattern pattern;
    private Matcher matcher;
    private OnDataTransfer24 mListener;
    private static final String REG_EXPRESSION =
            "^0{3}1\\d{3}(?:\\.\\d{2})?$";

    public static  Counterbefore newInstance() {

        Bundle args = new Bundle();

        Counterbefore fragment = new Counterbefore();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        index=false;
        View rootview = inflater.inflate(R.layout.fragment_counterbefoe, container, false);
        infTextView =rootview.findViewById(R.id.infTextView);
        infTextView.setTextSize(20);
        infTextView.setText(Html.fromHtml("<b><font color=\"red\">фрагмент: " +
                " </font> показания электросчетчика</b>"
                + "<i> 1 2 3</i>"
                + "<font color=\"red\"><b>4 </b></font>"
                + "<i>5</i>"
        ));
        quantity  = rootview.findViewById(R.id.quantity);
        btnqtsave = rootview.findViewById(R.id.btnqtsave);
        btnqtsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quantity = quantity.getText().toString();
                mListener.onDataTransfer24(Quantity);
                pattern = Pattern.compile(REG_EXPRESSION);
                matcher = pattern.matcher(Quantity);

                if (matcher.matches())
                    btnqttrans.setEnabled(!index);
                else Toast.makeText(getActivity(),"Укажите  правильные показания электросчетчика",
                        Toast.LENGTH_LONG).show();
            }});
        btnqttrans =rootview.findViewById(R.id.btnqttrans);
        btnqttrans.setEnabled(index);
        btnqttrans .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity) getActivity()).openNewContentFragment ( Choping.newInstance());
            }

        });


        return rootview;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDataTransfer24) {
            mListener = (OnDataTransfer24) context;
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

    public interface OnDataTransfer24 {
        // TODO: Update argument type and name
        void onDataTransfer24(String quantity);
    }
}
