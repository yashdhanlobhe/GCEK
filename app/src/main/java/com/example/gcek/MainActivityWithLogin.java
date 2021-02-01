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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.gcek.MainDrawer.DiscoverCollegeTab.AboutFragment;
import com.example.gcek.MainDrawer.ContributeTab.ContributeFragment;
import com.example.gcek.MainDrawer.FAQTab.MainFAQFragment;
import com.example.gcek.MainDrawer.MainHomeTab.HomeFragment;
import com.example.gcek.MainDrawer.PrivateMessages.PrivateMessagesFragment;
import com.example.gcek.MainDrawer.WerbsiteLoginsTab.VariousCollegeLoginFragment;
import com.example.gcek.MainDrawer.NotificationTab.NotificationFragment;
import com.example.gcek.MainDrawer.ShowIDTab.ShowID;
import com.example.gcek.MainDrawer.EventsTab.UpcomingFragment;
import com.example.gcek.MainDrawer.SettingTab.SettingFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import static com.example.gcek.Services.GetBarcode.getBarcodeFromString;


public class MainActivityWithLogin extends AppCompatActivity {
    public  static  UserData userData;
    public  static Drawable userimagedrawable;
    public Context  mcontext;
    DrawerLayout drawerLayout ;
    public  static Bitmap userImage ,BarcodeImage;
    ActionBarDrawerToggle toggleButton ;
    NavigationView navigationView ;
    FrameLayout frameLayout;
    public static String email;
    FirebaseFirestore db;
    Fragment temp ;
    int currentfragmentid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        getUserData();

        mcontext = this ;

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment temp ;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                currentfragmentid = item.getItemId();
                switch (item.getItemId()){
                    case R.id.nav_home:

                        temp = new HomeFragment();
                        replaceFragment(temp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_notifications:
                        temp = new NotificationFragment();
                        replaceFragment(temp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_showid:
                        temp = new ShowID();
                        replaceFragment(temp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_about:
                        temp = new AboutFragment();
                        replaceFragment(temp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_login:
                        temp = new VariousCollegeLoginFragment();
                        replaceFragment(temp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_setting:
                        temp = new SettingFragment();
                        replaceFragment(temp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_event:
                        temp = new UpcomingFragment();
                        replaceFragment(temp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_contribute:
                        temp = new ContributeFragment();
                        replaceFragment(temp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_faq:
                        temp = new MainFAQFragment();
                        replaceFragment(temp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_private_notifications:
                        temp = new PrivateMessagesFragment();
                        replaceFragment(temp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_Signout:
                        confirmsignout();
                        break;
                }
                return false;
            }
        });
    }
    public void replaceFragment(Fragment temp) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout , temp).commit();
    }

    private void getUserData() {
        db.collection("StudentUsers").document(FirebaseAuth.getInstance().getCurrentUser().getEmail())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userData =  documentSnapshot.toObject(UserData.class);
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
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        email = getIntent().getStringExtra("email");

        navigationView =findViewById(R.id.main_nav_view);
        drawerLayout = findViewById(R.id.drawerlayout);
        frameLayout= findViewById(R.id.main_frame_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout , new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);

        toggleButton = new ActionBarDrawerToggle(this , drawerLayout , toolbar , R.string.open , R.string.close);
        toggleButton.syncState();
        toggleButton.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HomeFragment());
            }
        });

        db = FirebaseFirestore.getInstance();

    }

    private class DownloadImageTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("UserImage" , "Starting Downloading");
            try {
                userImage= Picasso.get().load(userData.getProfileImage()).get();
                userimagedrawable = new BitmapDrawable(getResources(), userImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("UserImage" , "Downloaded");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            try {
//                ImageView userImage = findViewById(R.id.imageView);
//                userImage.setImageBitmap(MainActivityWithLogin.userImage);
//            }catch (Exception e){
//
//            }
        }
    }

    @Override
    public void onBackPressed() {
        if(currentfragmentid==R.id.nav_home){
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
        }
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout , new HomeFragment()).commit();
            currentfragmentid=R.id.nav_home;
        }
    }
    private void confirmsignout() {
        new android.app.AlertDialog.Builder(mcontext)
                .setMessage("Do you want to sign out ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(mcontext , MainActivityWithoutLogin.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }
}
