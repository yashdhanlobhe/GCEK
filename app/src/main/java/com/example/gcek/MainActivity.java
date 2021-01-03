package com.example.gcek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.NotificationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import com.example.gcek.maindrawer.HomeFragment;
import com.example.gcek.maindrawer.Notification_fragment;
import com.example.gcek.maindrawer.settingfragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout ;
    ActionBarDrawerToggle toggleButton ;
    NavigationView navigationView ;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView)findViewById(R.id.main_nav_view);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        frameLayout= findViewById(R.id.main_frame_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout , new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);

        toggleButton = new ActionBarDrawerToggle(this , drawerLayout , toolbar , R.string.open , R.string.close);
        toggleButton.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment temp ;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        temp = new HomeFragment();
                        break;
                    case R.id.nav_notifications:
                        temp = new Notification_fragment();
                        break;
                    case R.id.nav_setting:
                        temp = new settingfragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout , temp).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return  false;
            }
        });

    }
}
