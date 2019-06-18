package com.example.standartsolutionever;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity
        implements  Tab1.OnDataTransfer1,Type_ofRwm.OnDataTransfer2,
        Tab2.OnDataTransfer21,KindofRwm.OnDataTransfer3,Quantityfrag.OnDataTransfer4,
        Carrier.OnDataTransfer5, Cutting.OnDataTransfer23,QuantityforRefinary.OnDataTransfer22,
        Counterbefore.OnDataTransfer24,Choping.OnDataTransfer25, Crushing.OnDataTransfer26
{
   static String  slcproviders;
   static String  slctyperwm;
   static String  slckindrwm;
   static String  Quantity;
   static String   Carrier;
   ViewPager viewPager;
   MyPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        TabLayout tabLayout =  findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Приход сырья"));
        tabLayout.addTab(tabLayout.newTab().setText("Переработка сырья"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter= new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        }

    @Override public void onBackPressed (){
        if (! BackStackFragmn.handleBackPressed(getSupportFragmentManager())){
            super.onBackPressed();
        }
    }
    public void openNewContentFragment (Fragment fragment){
        RootFragment1 rootFragment1 = (RootFragment1) adapter.getItem(viewPager.getCurrentItem());
        if (fragment==Mainfragment.frag){
            rootFragment1.replaceFragmnent(fragment,false);
        }
        else    rootFragment1.replaceFragmnent(fragment,true);
    }

    @Override
    public void onDataTransfer1(String string) {
        slcproviders = string;
        }
    @Override
    public void onDataTransfer2 (String string){
        slctyperwm = string;
    }
    @Override
    public void onDataTransfer3(String string) {
        slckindrwm = string;
    }
    @Override
    public void onDataTransfer4 (String string){
     Quantity = string;
    }
    @Override
    public void onDataTransfer5 (String string) {
       Carrier = string;
        }
    @Override
    public void onDataTransfer21(String string){}
    @Override
    public void onDataTransfer23(String string) {
    }
    @Override
    public void onDataTransfer22(String quantity) {
    }

    @Override
    public void onDataTransfer24(String quantity) {
    }
    @Override
    public void  onDataTransfer25 (String quantity){}
    @Override
    public void  onDataTransfer26 (String quantity){}

}

