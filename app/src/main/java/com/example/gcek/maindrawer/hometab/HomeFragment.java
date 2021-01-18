package com.example.gcek.maindrawer.hometab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.R;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

List<Integer> lstImage = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_home, container, false);
        lstImage.add(R.drawable.f);
        lstImage.add(R.drawable.s);
        lstImage.add(R.drawable.t);
        HorizontalInfiniteCycleViewPager pager = (HorizontalInfiniteCycleViewPager) root.findViewById(R.id.HomeTabViewPager);
        SliderAdapter sliderAdapter = new SliderAdapter(lstImage , getActivity().getBaseContext());
        pager.setAdapter(sliderAdapter);
        return root;
    }
}