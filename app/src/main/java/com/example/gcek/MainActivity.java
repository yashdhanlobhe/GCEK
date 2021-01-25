package com.example.gcek;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.gcek.maindrawer.About.AboutFragment;
import com.example.gcek.maindrawer.ContributeFragment;
import com.example.gcek.maindrawer.hometab.HomeFragment;
import com.example.gcek.maindrawer.VariousCollegeLoginFragment;
import com.example.gcek.maindrawer.Notification_fragment;
import com.example.gcek.maindrawer.Showid;
import com.example.gcek.maindrawer.UpcomingFragment;
import com.example.gcek.maindrawer.setting.settingfragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import static com.example.gcek.resources.GetBarcode.getBarcodeFromString;


public class MainActivity extends AppCompatActivity {
    public  static  UserData userData;

    DrawerLayout drawerLayout ;
    public  static Bitmap userImage ,BarcodeImage;
    ActionBarDrawerToggle toggleButton ;
    NavigationView navigationView ;
    FrameLayout frameLayout;
    public static String email;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUI();
        getUserData();


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
                    case R.id.nav_showid:
                        temp = new Showid();
                        break;
                    case R.id.nav_about:
                        temp = new AboutFragment();
                        break;
                    case R.id.nav_login:
                        temp = new VariousCollegeLoginFragment();
                        break;
                    case R.id.nav_setting:
                        temp = new settingfragment();
                        break;
                    case R.id.nav_event:
                        temp = new UpcomingFragment();
                        break;
                    case R.id.nav_contribute:
                        temp = new ContributeFragment();
                        break;


                }
                replaceFragment(temp);
                drawerLayout.closeDrawer(GravityCompat.START);
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

        db = FirebaseFirestore.getInstance();

    }

    private class DownloadImageTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("UserImage" , "Starting Downloading");
            try {
                userImage= Picasso.get().load(userData.getProfileImage()).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("UserImage" , "Downloaded");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                ImageView userImage = findViewById(R.id.imageView);
                userImage.setImageBitmap(MainActivity.userImage);
            }catch (Exception e){

            }
        }
    }
}
