package com.example.gcek;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.gcek.Department.DepartmentMain;
import com.example.gcek.MainDrawer.AttendanceTab.CheckAttendance;
import com.example.gcek.MainDrawer.DiscoverCollegeTab.DiscoverActivity;
import com.example.gcek.MainDrawer.EventsTab.EventFragment;
import com.example.gcek.MainDrawer.FAQTab.FAQActivity;
import com.example.gcek.MainDrawer.MainHomeTab.HomeFragment;
import com.example.gcek.MainDrawer.NotificationTab.NewNotificationFragment;
import com.example.gcek.MainDrawer.NotificationTab.NotificationFragment;
import com.example.gcek.MainDrawer.SettingTab.SettingActivity;
import com.example.gcek.MainDrawer.ShowIDTab.ProfileActivity;
import com.example.gcek.MainDrawer.SignOut.SignOut;
import com.example.gcek.MainDrawer.WerbsiteLoginsTab.LoginsActivity;
import com.example.gcek.MainDrawer.publicForum.publicForumFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import static com.example.gcek.Services.GetBarcode.getBarcodeFromString;


public class MainActivityWithLogin extends AppCompatActivity {
    public static UserData userData;
    public static Drawable userimagedrawable;
    public static Bitmap userImage, BarcodeImage;
    public static String email;
    public Context mcontext;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggleButton;
    NavigationView navigationView;
    FrameLayout frameLayout;
    FirebaseFirestore db;
    BottomNavigationView bottomNavigationView;
    int currentfragmentid;

    @Override
    protected void onResume() {
        super.onResume();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcontext = this;
        initUI();
        getUserData();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                currentfragmentid = item.getItemId();
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        break;
                    case R.id.nav_showid:
                        startActivity(new Intent(mcontext, ProfileActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.nav_about:
                        startActivity(new Intent(mcontext, DiscoverActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.nav_login:
                        startActivity(new Intent(mcontext, LoginsActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.nav_setting:
                        startActivity(new Intent(mcontext, SettingActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.nav_faq:
                        startActivity(new Intent(mcontext, FAQActivity.class));
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.nav_Signout:
                        SignOut.confirmsignout(mcontext);
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                    case R.id.nav_department:
                        startActivity(new Intent(mcontext, DepartmentMain.class));
                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                        break;
                }
                return false;
            }
        });
    }


    private void getUserData() {
        db.collection("StudentUsers").document(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userData = documentSnapshot.toObject(UserData.class);
                FirebaseMessaging.getInstance().subscribeToTopic(userData.getGCEKID());
                BarcodeImage = getBarcodeFromString(userData.getGCEKID());
                setHeaderViewData();
                new DownloadImageTask().execute();
            }
        });

    }

    private void setHeaderViewData() {
        TextView headerViewid = findViewById(R.id.HeaderViewId);
        TextView headerClass = findViewById(R.id.HeaderViewClass);
        TextView headername = findViewById(R.id.HeaderViewnName);

        try {
            headername.setText(userData.getName());
            headerViewid.setText(userData.getGCEKID());
            headerClass.setText(userData.getBranch());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        email = getIntent().getStringExtra("email");
        navigationView = findViewById(R.id.main_nav_view);
        drawerLayout = findViewById(R.id.drawerlayout);
        frameLayout = findViewById(R.id.main_frame_layout);
        navigationView.setCheckedItem(R.id.nav_home);


        addBottomNavigationView();
        currentfragmentid = R.id.nav_home;
        toggleButton = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggleButton.syncState();
        toggleButton.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HomeFragment());
                bottomNavigationView.setSelectedItemId(R.id.nav_home);
            }
        });

        db = FirebaseFirestore.getInstance();

    }

    private void addBottomNavigationView() {
        bottomNavigationView = findViewById(R.id.MainActivityBottomNavView);
        replaceFragment(new HomeFragment());
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_notifications:
                        fragment = new NewNotificationFragment();
                        break;
                    case R.id.nav_publicForum:
                        fragment = new publicForumFragment();
                        break;
                    case R.id.nav_attendace:
                        fragment = new CheckAttendance();
                        break;
                    case R.id.nav_events:
                        fragment = new EventFragment();
                        break;
                }
                replaceFragment(fragment);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (currentfragmentid == R.id.nav_home) {
            new AlertDialog.Builder(mcontext)
                    .setMessage("Do you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivityWithLogin.super.onBackPressed();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, new HomeFragment()).commit();
            currentfragmentid = R.id.nav_home;
        }
    }



    public void replaceFragment(Fragment temp) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, temp).commit();
    }

    private class DownloadImageTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("UserImage", "Starting Downloading");
            try {
                userImage = Picasso.get().load(userData.getProfileImage()).get();
                userimagedrawable = new BitmapDrawable(getResources(), userImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("UserImage", "Downloaded");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
