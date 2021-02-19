package com.example.gcek.MainDrawer.ShowIDTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gcek.MainActivityWithLogin;
import com.example.gcek.R;

import static com.example.gcek.MainActivityWithLogin.email;
import static com.example.gcek.MainActivityWithLogin.userData;
import static com.example.gcek.MainActivityWithLogin.userimagedrawable;

public class ProfileActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.ShowIdactivitytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        try {
            TextView fullname = findViewById(R.id.FullNameID);
            fullname.setText(userData.getName());
            TextView GCEKid = findViewById(R.id.gcekID);
            GCEKid.setText(userData.getGCEKID());
            TextView passout = findViewById(R.id.passoutId);
            passout.setText(userData.getBatch());
            TextView branch = findViewById(R.id.BrachID);
            branch.setText(userData.getBranch());
            TextView emailtext = findViewById(R.id.EmailID);
            emailtext.setText(email);
            TextView phoneText = findViewById(R.id.PhoneNumberID);
            phoneText.setText(userData.getPhoneNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try
        {   ImageView userBarcode = findViewById(R.id.userBarcodeId);
            userBarcode.setImageBitmap(MainActivityWithLogin.BarcodeImage);

            ImageView userImage = findViewById(R.id.imageView);
            userImage.setImageDrawable(userimagedrawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }
}