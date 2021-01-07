package com.example.gcek.Notification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    public Toolbar toolbar;
    FirebaseDatabase firebaseDatabase ;
    public String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ID =  getIntent().getStringExtra("class");
        firebaseDatabase = FirebaseDatabase.getInstance();

        recyclerView = (RecyclerView)findViewById(R.id.mrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        toolbar = findViewById(R.id.clgNotification_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("College Notifications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        DatabaseReference data =getData(ID);
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notificationlist = new ArrayList<>();
                for(DataSnapshot db : snapshot.getChildren()){
                    NotificationData nd =db.getValue(NotificationData.class);
                    notificationlist.add(nd);
                }
                myAdapter = new MyAdapter(notificationlist);
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private DatabaseReference getData(String id) {
    switch (ID){
        case "college":
            return firebaseDatabase.getReference().child("Notices").child("college").getRef();
        case "fy":
            return firebaseDatabase.getReference().child("Notices").child("fy").getRef();
        case "sy":
            return firebaseDatabase.getReference().child("Notices").child("sy").getRef();
        case "ty":
            return firebaseDatabase.getReference().child("Notices").child("ty").getRef();
        case "finalyear":
            return firebaseDatabase.getReference().child("Notices").child("finalyear").getRef();
        case "tpo":
            return firebaseDatabase.getReference().child("Notices").child("tpo").getRef();
        default:
            return firebaseDatabase.getReference().child("Notices").child("college").getRef();
    }
    }
}