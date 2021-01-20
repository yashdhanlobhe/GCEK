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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_about, container, false);

        View.OnClickListener DepartmentClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch(v.getId()){
                    case R.id.ITCardViewDiscover:
                        intent = new Intent(getActivity().getApplicationContext() , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.itDepartmentBasicInfo));
                        break;
                    case R.id.EntcCardViewDiscover:
                        intent = new Intent(getActivity().getApplicationContext() , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.entcDepartmentBasicInfo));
                        break;
                    case R.id.CIVILCardViewDiscover:
                        intent = new Intent(getActivity().getApplicationContext() , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.mechDepartmentBasicInfo));
                        break;
                    case R.id.MECHCardViewDiscover:
                        intent = new Intent(getActivity().getApplicationContext() , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.civilDepartmentBasicInfo));
                        break;
                    case R.id.ElectricalCardViewDiscover:
                        intent = new Intent(getActivity().getApplicationContext() , DepartmentActivity.class).putExtra("data" , getResources().getStringArray(R.array.electricalDepartmentBasicInfo));
                        break;
                    case R.id.LocaitonCardView:
                        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 17.30966846043356, 74.18717468177438);
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        break;
                    case R.id.officialWebsiteCardView:
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gcekarad.ac.in/"));
                        break;
                }
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        view.findViewById(R.id.ITCardViewDiscover).setOnClickListener(DepartmentClickListner);
        view.findViewById(R.id.EntcCardViewDiscover).setOnClickListener(DepartmentClickListner);
        view.findViewById(R.id.CIVILCardViewDiscover).setOnClickListener(DepartmentClickListner);
        view.findViewById(R.id.MECHCardViewDiscover).setOnClickListener(DepartmentClickListner);
        view.findViewById(R.id.ElectricalCardViewDiscover).setOnClickListener(DepartmentClickListner);
        view.findViewById(R.id.LocaitonCardView).setOnClickListener(DepartmentClickListner);
        view.findViewById(R.id.officialWebsiteCardView).setOnClickListener(DepartmentClickListner);

        return view;
    }

}