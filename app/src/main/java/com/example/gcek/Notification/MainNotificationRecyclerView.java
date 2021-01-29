package com.example.gcek.Notification;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

public class MainNotificationRecyclerView extends AppCompatActivity implements OnNotifiacaitonClick {
    public List<NotificationDataClass> notificationlist;
    public RecyclerView recyclerView;
    public NotifiactionCardViewAdapter notifiactionCardViewAdapter;
    public ProgressDialog pd ;
    public Toolbar toolbar;
    FirebaseDatabase firebaseDatabase ;
    public String ID;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mcontext= this;
        pd = new ProgressDialog(mcontext);
        pd.show();
        pd.setCancelable(false);
        ID =  getIntent().getStringExtra("class");
        firebaseDatabase = FirebaseDatabase.getInstance();
        OnNotifiacaitonClick onNotifiacaitonClick = this;

        recyclerView = (RecyclerView)findViewById(R.id.mrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        toolbar = findViewById(R.id.clgNotification_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
                pd.dismiss();
                recyclerView.setAdapter(notifiactionCardViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private DatabaseReference getData(String id) {
        return firebaseDatabase.getReference().child("Notices").child(ID).getRef();
    }
    @Override
    public void OnClickListner(NotificationDataClass nd) {
        if (nd.noticeURI==null || nd.noticeURI == ""){
            Toast.makeText(mcontext , "Notice Not Available" , Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(new Intent(this , SeeNoticeImageActivity.class).putExtra("NoticeUrl" , nd.noticeURI));
    }
    @Override
    public void OnLongClickListner(NotificationDataClass nd) {
    }
}