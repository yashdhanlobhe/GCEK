package com.example.gcek.MainDrawer.AttendanceTab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

import static com.example.gcek.MainActivityWithLogin.userData;

public class CheckAttendance extends Fragment {
    RecyclerView mRecyclerView;
    Map<String, Object>  MarkedAttendace;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_check_attendance, container, false);

        mRecyclerView = root.findViewById(R.id.attendancerecyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        FirebaseFirestore.getInstance().collection("Attendance").document("IT").collection("FY").document(userData.getGCEKID().substring(6 , 8)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("xyz" , userData.getGCEKID().substring(7 , 8));
                MarkedAttendace= documentSnapshot.getData();
                CheckAttendaceAdapter mAdapter = new CheckAttendaceAdapter(MarkedAttendace);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        return root;
    }
}