package com.example.gcek.MainDrawer.NotificationTab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gcek.Notification.NotificationShowFragment;

public class PageAdapter extends FragmentPagerAdapter {

    int itemCount;

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        itemCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        String type;
        switch (position){
            case 1 :
                type = "TPO";
                break;
            case 2 :
                type = "fy";
                break;
            default :
                type = "college";
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putString("class", type);

        NotificationShowFragment fragobj = new NotificationShowFragment();
        fragobj.setArguments(bundle);

        return fragobj;
    }

    @Override
    public int getCount() {
        return itemCount;
    }
}
