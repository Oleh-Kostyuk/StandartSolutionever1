package com.example.standartsolutionever;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class RootFragment1 extends BackStackFragment {
    private Fragment fragment;
    private FragmentManager fm;
    int count;
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_root_fragment1, container, false);
        if (fragment != null){
            replaceFragmnent (fragment,false );
        }


        return view;
    }

    public void replaceFragmnent(Fragment fragment,boolean addToBackstack) {
       // Cleanbackstack(fm);

        if (addToBackstack){
           fm = getChildFragmentManager();
           FragmentTransaction ft = fm.beginTransaction();
            count = getChildFragmentManager().getBackStackEntryCount();
            Log.d(TAG,String.valueOf(count));
        //
           Log.d (TAG,String.valueOf(count));
                 ft. replace(R.id.root_frame1,fragment)
                .addToBackStack(null).commit();
           Log.d (TAG,String.valueOf(count));
          getChildFragmentManager().executePendingTransactions();

        }
        else {
            getChildFragmentManager().beginTransaction().
                replace(R.id.root_frame1,fragment).commit();
            if (count>0){
                  fm.popBackStack(fm.getBackStackEntryAt(0).getId(),FragmentManager.POP_BACK_STACK_INCLUSIVE);
                 }
        }


    }

   public void Cleanbackstack ( FragmentManager fm1 ){
      fm1 = getChildFragmentManager();
       count =fm1.getBackStackEntryCount();
       if (count>0){
           id = fm1.getBackStackEntryAt(0).getId();
       fm1.popBackStackImmediate(id,FragmentManager.POP_BACK_STACK_INCLUSIVE);}
   }
    public static RootFragment1 newInstance (Fragment fragment) {
        RootFragment1 rootfragment = new RootFragment1();
        rootfragment.fragment=fragment;
        return rootfragment;
    }
}
