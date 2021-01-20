package com.example.gcek.maindrawer.hometab;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
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
                try
                {
                    SliderAdapter sliderAdapter = new SliderAdapter(PosterList, getActivity().getBaseContext());
                    pager.setAdapter(sliderAdapter);
                }catch (Exception e){
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        View.OnClickListener socialmediaonclicklistner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = null;
                switch (v.getId()){
                    case R.id.facebooklogocontactcard:
                        try {
                            getActivity().getApplicationContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/109374122414004")));
                        } catch (Exception e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/pages/Government-College-of-Engineering-Karad/109374122414004")));
                        }
                        break;
                    case R.id.twitterlogocontactcard:
                        try {
                            getActivity().getApplicationContext().getPackageManager().getPackageInfo("com.linkedin.android", 0);
                             Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://profile/gcekarad/"));
                             startActivity(intent);
                        }catch (Exception e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.linkedin.com/in/gcekarad/")));
                        }
                        break;
                    case R.id.instalogocontactcard:
                        Uri uri = Uri.parse("https://www.instagram.com/gcek_gymkhana_official/");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                        likeIng.setPackage("com.instagram.android");
                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://www.instagram.com/gcek_gymkhana_official/")));
                        }
                        break;
                }
            }
        };
        root.findViewById(R.id.facebooklogocontactcard).setOnClickListener(socialmediaonclicklistner);
        root.findViewById(R.id.twitterlogocontactcard).setOnClickListener(socialmediaonclicklistner);
        root.findViewById(R.id.instalogocontactcard).setOnClickListener(socialmediaonclicklistner);

        return root;
    }
}