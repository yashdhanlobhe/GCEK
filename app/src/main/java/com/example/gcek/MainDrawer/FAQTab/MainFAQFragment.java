package com.example.gcek.MainDrawer.FAQTab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gcek.MainActivityWithLogin;
import com.example.gcek.R;

public class MainFAQFragment extends Fragment implements View.OnClickListener {
    Context mcontext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_f_a_qfragment, container, false);
         mcontext= getActivity().getBaseContext();
        root.findViewById(R.id.floatingbtntoaddfaq).setOnClickListener(this);
        root.findViewById(R.id.collegerelatedfaqcard).setOnClickListener(this);
        root.findViewById(R.id.examrelatedfaqcard).setOnClickListener(this);
        root.findViewById(R.id.hostelrelatedfaqcard).setOnClickListener(this);
        root.findViewById(R.id.admissionrelatedfaqcard).setOnClickListener(this);
        root.findViewById(R.id.scholarshiprelatedfaqcard).setOnClickListener(this);
        root.findViewById(R.id.tporelatedfaqcard).setOnClickListener(this);
        root.findViewById(R.id.otherrelatedfaqcard).setOnClickListener(this);
        root.findViewById(R.id.unansweredrelatedfaqcard).setOnClickListener(this);
        return root;

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.floatingbtntoaddfaq:
                ((MainActivityWithLogin) getActivity()).replaceFragment(new FaqAddFragment());
                break;
            case R.id.collegerelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "college"));
                break;
            case R.id.examrelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "exam"));
                break;
            case R.id.hostelrelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "hostel"));
                break;
            case R.id.admissionrelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "admission"));
                break;
            case R.id.scholarshiprelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "scholarship"));
                break;
            case R.id.tporelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "tpo"));
                break;
            case R.id.otherrelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "other"));
                break;
            case R.id.unansweredrelatedfaqcard:
                startActivity(new Intent(mcontext, FAQShowingActivity.class).putExtra("name", "unanswered"));
                break;
        }
    }
}