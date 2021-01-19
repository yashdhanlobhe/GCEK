package com.example.gcek.maindrawer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.gcek.DepartmentActivity;
import com.example.gcek.R;

import java.util.Locale;

public class AboutFragment extends Fragment {
CardView it , mech ,entc, electrical , civil , location , officialWebsite;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context mcontext;

        View view =inflater.inflate(R.layout.fragment_about, container, false);
        it = view.findViewById(R.id.ITCardViewDiscover);
        entc = view.findViewById(R.id.EntcCardViewDiscover);
        civil = view.findViewById(R.id.CIVILCardViewDiscover);
        mech = view.findViewById(R.id.MECHCardViewDiscover);
        electrical = view.findViewById(R.id.ElectricalCardViewDiscover);
        location = view.findViewById(R.id.LocaitonCardView);
        officialWebsite = view.findViewById(R.id.officialWebsiteCardView);

        View.OnClickListener DepartmentClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.ITCardViewDiscover:
                        startActivity(new Intent(getActivity().getApplicationContext() , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.itDepartmentBasicInfo)));
                        break;
                    case R.id.EntcCardViewDiscover:
                        startActivity(new Intent(getActivity().getApplicationContext() , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.entcDepartmentBasicInfo)));
                        break;
                    case R.id.CIVILCardViewDiscover:
                        startActivity(new Intent(getActivity().getApplicationContext() , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.mechDepartmentBasicInfo)));
                        break;
                    case R.id.MECHCardViewDiscover:
                        startActivity(new Intent(getActivity().getApplicationContext() , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.civilDepartmentBasicInfo)));
                        break;
                    case R.id.ElectricalCardViewDiscover:
                        startActivity(new Intent(getActivity().getApplicationContext() , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.electricalDepartmentBasicInfo)));
                        break;
                }
            }
        };
        it.setOnClickListener(DepartmentClickListner);
        entc.setOnClickListener(DepartmentClickListner);
        civil.setOnClickListener(DepartmentClickListner);
        electrical.setOnClickListener(DepartmentClickListner);
        mech.setOnClickListener(DepartmentClickListner);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 17.30966846043356, 74.18717468177438);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
        officialWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gcekarad.ac.in/"));
                startActivity(intent);
            }
        });
        return view;
    }

}