package com.example.gcek.MainDrawer.publicForum;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.Notification.NotifiactionCardViewAdapter;
import com.example.gcek.Notification.NotificationDataClass;
import com.example.gcek.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.gcek.InitiateAppData.mfirebaseDatabase;


public class publicForumFragment extends Fragment {

    ArrayList<PublicForumItemData> publicForumItemData;
    PublicForumAdapter publicForumAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_public_forum, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.PublicForumRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        root.findViewById(R.id.floatingbuttontoaddpublicforum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext() , AddPublicForumActivity.class));
            }
        });
        mfirebaseDatabase.getReference().child("publicForum").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                publicForumItemData = new ArrayList<>();
                for(DataSnapshot db : snapshot.getChildren()){
                    PublicForumItemData nd =db.getValue(PublicForumItemData.class);
                    publicForumItemData.add(nd);
                }
                publicForumAdapter = new PublicForumAdapter(publicForumItemData);
                recyclerView.setAdapter(publicForumAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return  root ;

    }
}