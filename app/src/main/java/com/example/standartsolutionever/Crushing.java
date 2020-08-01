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
import static com.example.standartsolutionever.Mainfragment.frag;

public class Crushing extends Fragment {
    Boolean index;
    TextView infTextView;
    ListView lvData;
    Button btncarsave;
    Button btncartrans;
    String slccarrier;
    private OnDataTransfer26 mListener;


    public static Crushing newInstance() {

        Bundle args = new Bundle();

        Crushing fragment = new Crushing();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        index = false;
        View rootview = inflater.inflate(R.layout.fragment_crushing, container, false);
        final String[] Carriers = {" Дробление Да", " Дробление Нет"};
        infTextView =rootview.findViewById(R.id.infTextView);
        infTextView.setTextSize(20);
        infTextView.setText(Html.fromHtml("<b><font color=\"red\">фрагмент: " +
                " </font> дробение </b>"
                + "<i> 1 2 3 4</i>"
                + "<font color=\"red\"><b>5 </b></font>"
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
                mListener.onDataTransfer26(slccarrier);
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
                frag =Tab2.newInstance();
                ((MainActivity) getActivity()).openNewContentFragment ( frag);
             //   ((MainActivity) getActivity()).openNewContentFragment ( Type_ofRwm.newInstance());
            }
        });



        return rootview;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDataTransfer26) {
            mListener = (OnDataTransfer26) context;
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

    public interface OnDataTransfer26 {
        // TODO: Update argument type and name
        void onDataTransfer26(String string);
    }

}
