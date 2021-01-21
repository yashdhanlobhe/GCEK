package com.example.gcek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.gcek.maindrawer.About.AboutFragment;
import com.example.gcek.maindrawer.LoginFragmentWTO;
import com.example.gcek.maindrawer.MainNoticeWto;
import com.example.gcek.maindrawer.Notification_fragment;
import com.example.gcek.maindrawer.UpcomingFragment;
import com.example.gcek.maindrawer.hometab.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FirstActivityWto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_wto);
        BottomNavigationView bottomNavigationView = findViewById(R.id.MainActivityWTOBottomNavView);
        getSupportFragmentManager().beginTransaction().replace(R.id.MainActivityWTOFrameLayout , new LoginFragmentWTO()).commit();
        bottomNavigationView.setSelectedItemId(R.id.nav_Login_wto);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_about_wto:
                        fragment = new AboutFragment();
                        break;
                    case R.id.nav_Notification_wto:
                        fragment = new MainNoticeWto();
                        break;
                    case R.id.nav_home_wto:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_event_wto:
                        fragment = new UpcomingFragment();
                        break;
                    case R.id.nav_Login_wto:
                        fragment = new LoginFragmentWTO();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.MainActivityWTOFrameLayout , fragment).commit();
                return true;
            }
        });
    }
}