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
public class Carrier extends Fragment {
    Boolean index;
    TextView infTextView;
    ListView lvData;
    Button btncarsave;
    Button btncartrans;
    String slccarrier;
    private OnDataTransfer5 mListener;

   /* public Carrier () {

    };
*/
    public static Carrier newInstance() {

       Bundle args = new Bundle();

       Carrier fragment = new Carrier();
       fragment.setArguments(args);
       return fragment;
   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

            index = false;
            View rootview = inflater.inflate(R.layout.fragment_carrier, container, false);
        infTextView =rootview.findViewById(R.id.infTextView);
        infTextView.setTextSize(20);
        infTextView.setText(Html.fromHtml("<b><font color=\"red\"> фрагмент:  </font>выбор перевозчика </b>"
                + "<i>1</i>"
                + "<font color=\"red\"><b>2 </b></font>"
                + "<i>3 4 5 6 </i>"));
            final String[] Carriers = {"Самовывоз", "Поставщик", "Услуга"};
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
                    mListener.onDataTransfer5(slccarrier);
                    if(!isEmpty(slccarrier)) btncartrans.setEnabled(!index);
                    else Toast.makeText(getActivity(),"Выбрать перевозчика",Toast.LENGTH_LONG).show();
    }
});

            // устанавливаем для списка адаптер
            btncartrans = rootview.findViewById(R.id.btncardtrans);
            btncartrans.setEnabled(index);
           btncartrans.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* FragmentTransaction transaction = getFragmentManager()
                            .beginTransaction();

                    transaction.replace(R.id.root_frame1, new Type_ofRwm());
                    transaction.addToBackStack(null);
                    transaction.commit(); */
                    ((MainActivity) getActivity()).openNewContentFragment ( Type_ofRwm.newInstance());
                }
            });



            return rootview;
        }
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof OnDataTransfer5) {
                mListener = (OnDataTransfer5) context;
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

        public interface OnDataTransfer5 {
            // TODO: Update argument type and name
            void onDataTransfer5(String string);
        }

    }


