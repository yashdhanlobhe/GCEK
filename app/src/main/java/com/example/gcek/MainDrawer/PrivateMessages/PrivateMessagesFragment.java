package com.example.gcek.MainDrawer.PrivateMessages;

import android.app.ProgressDialog;
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
import com.example.gcek.Notification.OnNotifiacaitonClick;
import com.example.gcek.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.gcek.MainActivityWithLogin.userData;

public class PrivateMessagesFragment extends Fragment implements OnNotifiacaitonClick {
    public List<NotificationDataClass> notificationlist;
    public RecyclerView recyclerView;
    public NotifiactionCardViewAdapter notifiactionCardViewAdapter;
    public ProgressDialog pd ;
    FirebaseDatabase firebaseDatabase ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_private_messages, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        pd = new ProgressDialog(root.getContext());
        pd.show();
        OnNotifiacaitonClick onNotifiacaitonClick = this;
        recyclerView = (RecyclerView)root.findViewById(R.id.privatenotificationrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        DatabaseReference data = firebaseDatabase.getReference().child("privatemessage").child(userData.getGCEKID()).getRef();
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notificationlist = new ArrayList<>();
                for(DataSnapshot db : snapshot.getChildren()){
                    NotificationDataClass nd =db.getValue(NotificationDataClass.class);
                    notificationlist.add(nd);
                }
                pd.dismiss();
                notifiactionCardViewAdapter = new NotifiactionCardViewAdapter(notificationlist , onNotifiacaitonClick);
                recyclerView.setAdapter(notifiactionCardViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }

    @Override
    public void OnClickListner(NotificationDataClass nd) {
        }

    @Override
    public void OnLongClickListner(NotificationDataClass nd) {
    }
}
