package com.example.standartsolutionever;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private  final List <String> tabTitles = new ArrayList<String>(){{
        add  ("Приход сырья");
        add ("Переработка сырья");


    }};
    private List<Fragment> tabs =new ArrayList<>();
    public MyPagerAdapter(FragmentManager fm){
        super(fm);
        initialiseTabs ();
    }
    private void initialiseTabs (){
        tabs.add(RootFragment1.newInstance(Tab1.newInstance()));
        tabs.add(RootFragment1.newInstance(Tab2.newInstance()));
    }
    @Override    public Fragment getItem(int position) {
      /*  switch (position){
            case 0: return      RootFragment1.newInstance(Tab1.newInstance() );
            case 1: return      RootFrgment2.newInstance(Tab1.newInstance());
        }


        return null;*/
      return tabs.get(position);
    }

    @Override
    public int getCount() {

        return tabs.size();
    }

    @Override    public CharSequence getPageTitle(int position) {

       return tabTitles.get(position);
    }
}
