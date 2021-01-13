package com.example.gcek;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.gcek.maindrawer.HomeFragment;
import com.example.gcek.maindrawer.Notification_fragment;
import com.example.gcek.maindrawer.Showid;
import com.example.gcek.maindrawer.settingfragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.sql.DriverPropertyInfo;

public class MainActivity extends AppCompatActivity {
    public  static  UserData userData;
    DrawerLayout drawerLayout ;
    public  static Bitmap userImage;
    ActionBarDrawerToggle toggleButton ;
    NavigationView navigationView ;
    FrameLayout frameLayout;
    String email;
    FirebaseFirestore db;
    StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.main_toolbar);
        db = FirebaseFirestore.getInstance();
        setSupportActionBar(toolbar);
        email = getIntent().getStringExtra("email");
//        db.collection("StudentUsers").document(email)
//                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                userData =  documentSnapshot.toObject(UserData.class);
//            }
//        });
        navigationView = (NavigationView)findViewById(R.id.main_nav_view);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        frameLayout= findViewById(R.id.main_frame_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout , new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);

        toggleButton = new ActionBarDrawerToggle(this , drawerLayout , toolbar , R.string.open , R.string.close);
        toggleButton.syncState();
        toggleButton.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
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
                    case R.id.nav_showid:
                        temp = new Showid();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout , temp).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
}
