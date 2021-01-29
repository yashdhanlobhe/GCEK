package com.example.gcek.MainDrawer.NotificationTab;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.Notification.MainNotificationRecyclerView;
import com.example.gcek.R;

public class NotificationFragment extends Fragment {

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification_fragment, container, false);

        CardView college = root.findViewById(R.id.collegeNotificationCARD);
        CardView fy = root.findViewById(R.id.fycardview);
        CardView sy = root.findViewById(R.id.sycardview);
        CardView ty = root.findViewById(R.id.tycardview);
        CardView finalyear = root.findViewById(R.id.finalyearcardview);
        CardView tpo = root.findViewById(R.id.tpocardview);

        college.setTag("college");
        fy.setTag("fy");
        sy.setTag("sy");
        ty.setTag("ty");
        finalyear.setTag("finalyear");
        tpo.setTag("tpo");


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , MainNotificationRecyclerView.class).putExtra("class", (String) v.getTag()));
            }
        };
        college.setOnClickListener(onClickListener);
        fy.setOnClickListener(onClickListener);
        sy.setOnClickListener(onClickListener);
        ty.setOnClickListener(onClickListener);
        finalyear.setOnClickListener(onClickListener);
        tpo.setOnClickListener(onClickListener);
        return root;
    }

}