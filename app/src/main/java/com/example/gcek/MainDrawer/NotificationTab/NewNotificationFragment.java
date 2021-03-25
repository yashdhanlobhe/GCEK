package com.example.gcek.MainDrawer.NotificationTab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class NewNotificationFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem mclass,tpo,college;
    PageAdapter pageAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root  = inflater.inflate(R.layout.fragment_new_notification, container, false);

        viewPager = root.findViewById(R.id.viewPagerNotification);
        mclass = root.findViewById(R.id.classTabNotification);
        tpo = root.findViewById(R.id.TPOTabNotification);
        college = root.findViewById(R.id.collegeTabNotification);
        tabLayout = root.findViewById(R.id.tabLayoutNotification);
        pageAdapter = new PageAdapter(getActivity().getSupportFragmentManager() , tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0||tab.getPosition() == 1||tab.getPosition() == 2){
                    pageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        return root;
    }
}