package com.example.gcek.maindrawer;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.Notification.Notification;
import com.example.gcek.R;

public class Notification_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Notification_fragment() {
        // Required empty public constructor
    }
    public static Notification_fragment newInstance(String param1, String param2) {
        Notification_fragment fragment = new Notification_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
                startActivity(new Intent(getActivity() , Notification.class).putExtra("class", (String) v.getTag()));
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