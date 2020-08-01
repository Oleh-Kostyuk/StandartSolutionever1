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
    public class QuantityforRefinary extends Fragment {
        Boolean index;
        TextView infTextView;
        EditText quantity;
        Button btnqttrans;
        Button btnqtsave;
        String Quantity;
        private com.example.standartsolutionever.QuantityforRefinary.OnDataTransfer22 mListener;


        public static com.example.standartsolutionever.QuantityforRefinary newInstance() {

            Bundle args = new Bundle();

            com.example.standartsolutionever.QuantityforRefinary fragment = new com.example.standartsolutionever.QuantityforRefinary();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            index=false;
            View rootview = inflater.inflate(R.layout.fragment_quantityfor_refinary, container, false);
            infTextView =rootview.findViewById(R.id.infTextView);
            infTextView.setTextSize(20);
            infTextView.setText(Html.fromHtml("<b><font color=\"red\">фрагмент: " +
                    " </font>количество сырья </b>"
                    + "<i> 1   </i>"
                    + "<font color=\"red\"><b>2 </b></font>"
                    + "<i>3 4 5 </i>"));
            quantity  = rootview.findViewById(R.id.quantity);
            btnqtsave = rootview.findViewById(R.id.btnqtsave);
            btnqtsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Quantity = quantity.getText().toString();
                    mListener.onDataTransfer22(Quantity);
                    if (!isEmpty(Quantity))btnqttrans.setEnabled(!index);
                    else Toast.makeText(getActivity(),"Укажите количество",Toast.LENGTH_LONG).show();
                }});
            btnqttrans =rootview.findViewById(R.id.btnqttrans);
            btnqttrans.setEnabled(index);
            btnqttrans .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ((MainActivity) getActivity()).openNewContentFragment (Cutting.newInstance());
                }

            });


            return rootview;
        }
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof com.example.standartsolutionever.QuantityforRefinary.OnDataTransfer22) {
                mListener = (com.example.standartsolutionever.QuantityforRefinary.OnDataTransfer22) context;
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

        public interface OnDataTransfer22 {
            // TODO: Update argument type and name
            void onDataTransfer22(String quantity);
        }
    }
