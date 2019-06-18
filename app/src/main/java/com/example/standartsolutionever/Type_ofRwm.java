package com.example.standartsolutionever;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.security.InvalidParameterException;

import static android.text.TextUtils.isEmpty;
import static com.example.standartsolutionever.RwmContract.TypeOfRwmEntry.TABLE_NAME;
import static android.content.Context.MODE_PRIVATE;
import static com.example.standartsolutionever.RwmContract.CONTENT_AUTHORITY_URI;




public class Type_ofRwm extends Fragment
{
    Boolean index;
    String slctyperwm;
    ListView lvData;
    Button btntype;
    Button btntype2;
    private OnDataTransfer2  mListener;
  /*  public Type_ofRwm() {
        // Required empty public constructor
    }
*/
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
        final String[] TypeofRwm ={"Дуб","Сосна","Липа","Клен","Тополь","Фруктовые","Солома"};
        lvData = rootview.findViewById(R.id.TypeOfRwm);

        final ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_multiple_choice, TypeofRwm);

        // устанавливаем для списка адаптер
        lvData.setAdapter(adapter);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               slctyperwm = "";
                SparseBooleanArray sp = lvData.getCheckedItemPositions();
                for ( int i=0; i<TypeofRwm.length; i++ ){
                    if (sp.get(i))
                        slctyperwm += TypeofRwm [i]+ " " ;}

                }

            });
        btntype =rootview.findViewById(R.id.btntype);
        btntype.setEnabled(index);
        btntype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();

                transaction.replace(R.id.root_frame1, new KindofRwm());
                transaction.addToBackStack(null);
                transaction.commit();*/
                ((MainActivity) getActivity()).openNewContentFragment ( KindofRwm.newInstance());


            }

        });
        btntype2 = rootview.findViewById(R.id.btntype2);
        btntype2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mListener.onDataTransfer2(slctyperwm);
               if (!isEmpty(slctyperwm)) btntype.setEnabled(!index);
               else Toast.makeText(getActivity(),"Выбрать тип сырья",Toast.LENGTH_LONG).show();
            }
        });
        return  rootview;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDataTransfer2) {
            mListener = (OnDataTransfer2) context;
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

    public interface OnDataTransfer2 {
        // TODO: Update argument type and name
        void onDataTransfer2(String string);
    }
}