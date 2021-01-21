package com.example.gcek.maindrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.Notification.MyAdapter;
import com.example.gcek.Notification.NotificationData;
import com.example.gcek.Notification.OnNotifiacaitonClick;
import com.example.gcek.Notification.SeeNotificationImage;
import com.example.gcek.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainNoticeWto extends Fragment implements OnNotifiacaitonClick
{
    public List<NotificationData> notificationlist;
    public RecyclerView recyclerView;
    public MyAdapter myAdapter;
    public ProgressDialog pd ;
    FirebaseDatabase firebaseDatabase ;
    public String ID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_notice_wto, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        pd = new ProgressDialog(root.getContext());
        pd.show();
        OnNotifiacaitonClick onNotifiacaitonClick = this;
        recyclerView = (RecyclerView)root.findViewById(R.id.mrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        DatabaseReference data = firebaseDatabase.getReference().child("Notices").child("college").getRef();;
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notificationlist = new ArrayList<>();
                for(DataSnapshot db : snapshot.getChildren()){
                    NotificationData nd =db.getValue(NotificationData.class);
                    notificationlist.add(nd);
                }
                pd.dismiss();
                myAdapter = new MyAdapter(notificationlist , onNotifiacaitonClick);
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }

    @Override
    public void OnClickListner(NotificationData nd) {
        startActivity(new Intent(getActivity().getApplicationContext() , SeeNotificationImage.class).putExtra("NoticeUrl" , nd.noticeURI));
    }

    @Override
    public void OnLongClickListner(NotificationData nd) {

    }
}