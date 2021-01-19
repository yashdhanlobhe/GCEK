package com.example.gcek.maindrawer.hometab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.Notification.MyAdapter;
import com.example.gcek.Notification.NotificationData;
import com.example.gcek.R;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<Integer> lstImage = new ArrayList<>();
    List<PosterData> PosterList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        View root =  inflater.inflate(R.layout.fragment_home, container, false);

        DatabaseReference data = firebaseDatabase.getReference().child("HomePoster").getRef();
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PosterList = new ArrayList<>();
                for(DataSnapshot db : snapshot.getChildren()){
                    PosterData nd =db.getValue(PosterData.class);
                    PosterList.add(nd);
                }
                HorizontalInfiniteCycleViewPager pager = (HorizontalInfiniteCycleViewPager) root.findViewById(R.id.HomeTabViewPager);
                SliderAdapter sliderAdapter = new SliderAdapter(PosterList , getActivity().getBaseContext());
                pager.setAdapter(sliderAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }
}