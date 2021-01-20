package com.example.gcek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.gcek.maindrawer.AboutFragment;
import com.example.gcek.maindrawer.ClubsFragment;
import com.example.gcek.maindrawer.LoginFragmentWTO;
import com.example.gcek.maindrawer.UpcomingFragment;
import com.example.gcek.maindrawer.hometab.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FirstActivityWto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_wto);
        BottomNavigationView bottomNavigationView = findViewById(R.id.MainActivityWTOBottomNavView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_about_wto:
                        fragment = new AboutFragment();
                        break;
//                    case R.id.nav_club_wto:
//                        fragment = new ClubsFragment();
//                        break;
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
        getSupportFragmentManager().beginTransaction().replace(R.id.MainActivityWTOFrameLayout , new LoginFragmentWTO()).commit();
    }
}