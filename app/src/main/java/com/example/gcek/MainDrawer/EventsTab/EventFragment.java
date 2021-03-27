package com.example.gcek.MainDrawer.EventsTab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EventFragment extends Fragment {
    ArrayList<EventData> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        data = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.eventRecylclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        FirebaseDatabase.getInstance().getReference().child("Events")
                .get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren() ){
                    data.add(dataSnapshot1.getValue(EventData.class));
//                    Log.d("yd7" , dataSnapshot1.getValue(EventData.class).toString());
                    recyclerView.setAdapter(new EventRecyclerAdapter(data));
                }
            }
        });


        return view;
    }
}