package com.example.gcek.Notification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gcek.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationShowFragment extends Fragment implements OnNotifiacaitonClick {
    public List<NotificationDataClass> notificationlist;
    public RecyclerView recyclerView;
    public NotifiactionCardViewAdapter notifiactionCardViewAdapter;
    public ProgressDialog pd ;
    FirebaseDatabase firebaseDatabase ;
    public String ID;
    Context mcontext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification_show, container, false);

        mcontext= container.getContext();
        pd = new ProgressDialog(mcontext);
//        pd.show();
        pd.setCancelable(false);
        ID =  getArguments().getString("class");
        firebaseDatabase = FirebaseDatabase.getInstance();
        OnNotifiacaitonClick onNotifiacaitonClick = this;

        recyclerView =root.findViewById(R.id.mrecyclerviewNotification);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mcontext);
        recyclerView.setLayoutManager(layoutManager);


        DatabaseReference data = getData(ID);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notificationlist = new ArrayList<>();
                for(DataSnapshot db : snapshot.getChildren()){
                    NotificationDataClass nd =db.getValue(NotificationDataClass.class);
                    notificationlist.add(nd);
                }
                notifiactionCardViewAdapter = new NotifiactionCardViewAdapter(notificationlist , onNotifiacaitonClick);
//                pd.dismiss();
                recyclerView.setAdapter(notifiactionCardViewAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    return root;
    }

    private DatabaseReference getData(String id) {
        return firebaseDatabase.getReference().child("Notices").child(id).getRef();
    }

    @Override
    public void OnClickListner(NotificationDataClass nd) {
        if (nd.noticeURI==null || nd.noticeURI == ""){
            Toast.makeText(mcontext , "Notice Not Available" , Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(new Intent(mcontext , SeeNoticeImageActivity.class).putExtra("NoticeUrl" , nd.noticeURI));
    }

    @Override
    public void OnLongClickListner(NotificationDataClass nd) {

    }
}