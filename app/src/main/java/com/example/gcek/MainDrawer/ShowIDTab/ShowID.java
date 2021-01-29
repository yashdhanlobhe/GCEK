package com.example.gcek.MainDrawer.ShowIDTab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gcek.MainActivityWithLogin;
import com.example.gcek.R;

import static com.example.gcek.MainActivityWithLogin.email;
import static com.example.gcek.MainActivityWithLogin.userData;

public class ShowID extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_showid, container, false);

        try {
            TextView fullname = root.findViewById(R.id.FullNameID);
            fullname.setText(userData.getName());
            TextView GCEKid = root.findViewById(R.id.gcekID);
            GCEKid.setText(userData.getGCEKID());
            TextView passout = root.findViewById(R.id.passoutId);
            passout.setText(userData.getBatch());
            TextView branch = root.findViewById(R.id.BrachID);
            branch.setText(userData.getBranch());
            TextView emailtext = root.findViewById(R.id.EmailID);
            emailtext.setText(email);
            TextView phoneText = root.findViewById(R.id.PhoneNumberID);
            phoneText.setText(userData.getPhoneNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try
        {   ImageView userBarcode = root.findViewById(R.id.userBarcodeId);
            userBarcode.setImageBitmap(MainActivityWithLogin.BarcodeImage);

            ImageView userImage = root.findViewById(R.id.imageView);
            userImage.setImageBitmap(MainActivityWithLogin.userImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }
}