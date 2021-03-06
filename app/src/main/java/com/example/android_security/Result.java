package com.example.android_security;

import java.util.ArrayList;
import com.example.android_security.MainActivity.PInfo;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Window;


public class Result extends FragmentActivity implements ActionBar.TabListener{

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private String title;
    public ArrayList<PInfo> lista;
    public ArrayList<PInfo> can_cost_money_obj;
    public ArrayList<PInfo> can_see_personal_info_obj;
    public ArrayList<PInfo> can_impact_battery_obj;
    public ArrayList<PInfo> can_change_system_obj;
    public ArrayList<PInfo> can_see_location_info_obj;
    public ArrayList<PInfo> has_majority;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.result);


        can_cost_money_obj = getIntent().getParcelableArrayListExtra("can_cost_money_obj");
        can_see_personal_info_obj = getIntent().getParcelableArrayListExtra("can_see_personal_info_obj");
        can_impact_battery_obj = getIntent().getParcelableArrayListExtra("can_impact_battery_obj");
        can_change_system_obj = getIntent().getParcelableArrayListExtra("can_change_system_obj");
        can_see_location_info_obj = getIntent().getParcelableArrayListExtra("can_see_location_info_obj");
        has_majority = getIntent().getParcelableArrayListExtra("has_majority");
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
            final ActionBar actionBar = getActionBar();
            //actionBar.setHomeButtonEnabled(true);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                actionBar.setSelectedNavigationItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        //actionBar.addTab(actionBar.newTab().setText("Summary").setTabListener(this));
    }
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }


    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    lista = can_cost_money_obj;
                    break;
                case 1:
                    lista = can_see_personal_info_obj;
                    break;
                case 2:
                    lista = can_impact_battery_obj;
                    break;
                case 3:
                    lista = can_change_system_obj;
                    break;
                case 4:
                    lista = can_see_location_info_obj;
                    break;
                case 5:
                    lista = has_majority;
                    break;
            }

            ScreenSlidePageFragment myfrag =  new ScreenSlidePageFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("list",lista);
            myfrag.setArguments(bundle);
            return myfrag;
        }

        @Override
        public int getCount() {
            return 6;
        }

        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0:
                    title = "CAN COST ME MONEY";
                    break;
                case 1:
                    title = "CAN SEE PERSONAL INFO";
                    break;
                case 2:
                    title = "CAN IMPACT BATTERY";
                    break;
                case 3:
                    title = "CAN CHANGE SYSTEM";
                    break;
                case 4:
                    title = "CAN SEE LOCATION INFO";
                    break;
                case 5:
                    title = "Summary of All apps";
            }
            return title;
        }
    }
}