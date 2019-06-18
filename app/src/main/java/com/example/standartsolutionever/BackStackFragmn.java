package com.example.standartsolutionever;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public abstract class BackStackFragmn extends Fragment {
    String TAG = "tag";
    int count;
    int index;
public static boolean handleBackPressed (FragmentManager fm){
    if (fm.getFragments() != null) {
        for (Fragment frag : fm.getFragments()){
            if (frag != null && frag.isVisible() && frag instanceof BackStackFragmn){
                if (((BackStackFragmn)frag).onBackPressed ()){
                    return true;
                }
            }
        }
    }
    return false;
}
protected boolean onBackPressed(){
    FragmentManager fm = getChildFragmentManager();
    if (handleBackPressed(fm)){
        return true ;
    }
    else if (getUserVisibleHint() && fm.getBackStackEntryCount()>0){
       // fm.popBackStack();
      count = fm.getBackStackEntryCount();
      index = count-1;

    //    Log.d(TAG, String.valueOf(count));
      fm.popBackStackImmediate(fm.getBackStackEntryAt(index).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
      Log.d (TAG,String.valueOf(count));
        return true;
    }
    return false;
}
}
