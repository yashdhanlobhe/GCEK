package com.example.gcek.Notification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gcek.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {
    public List<NotificationData> notificationlist;
    public RecyclerView recyclerView;
    public MyAdapter myAdapter;
    public ProgressDialog pd ;
    FirebaseDatabase firebaseDatabase ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        pd = new ProgressDialog(this );
        recyclerView = (RecyclerView)findViewById(R.id.mrecyclerview);
        firebaseDatabase = FirebaseDatabase.getInstance();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DatabaseReference data =firebaseDatabase.getReference().child("Notices").getRef();
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notificationlist = new ArrayList<>();
                pd.show();
                for(DataSnapshot db : snapshot.getChildren()){
                    NotificationData nd =db.getValue(NotificationData.class);
                    notificationlist.add(nd);
                }
                myAdapter = new MyAdapter(notificationlist);
                pd.dismiss();
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}