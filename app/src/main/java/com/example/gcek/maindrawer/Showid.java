package com.example.gcek.maindrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gcek.MainActivity;
import com.example.gcek.R;
import com.squareup.picasso.Picasso;

import static android.content.Context.POWER_SERVICE;
import static com.example.gcek.MainActivity.userData;

public class Showid extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_showid, container, false);
//        TextView fullname=root.findViewById(R.id.FullNameID);
//        fullname.setText(userData.getName());
//        TextView GCEKid=root.findViewById(R.id.gcekID);
//        GCEKid.setText(userData.getGCEKID());
//        TextView passout=root.findViewById(R.id.passoutId);
//        passout.setText(userData.getBatch());
//        TextView branch=root.findViewById(R.id.BrachID);
//        branch.setText(userData.getBranch());
//        ImageView userImage = root.findViewById(R.id.imageView);
        return root;
    }
}