package com.example.gcek.MainDrawer.publicForum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gcek.R;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;

import static com.example.gcek.InitiateAppData.mfirebaseDatabase;
import static com.example.gcek.MainActivityWithLogin.userData;

public class AddPublicForumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_public_forum);

        TextView title = findViewById(R.id.titlepublicForumADD);
        TextView description = findViewById(R.id.DescriptionpublicForumADD);

        findViewById(R.id.addpublicforumbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReferenc = mfirebaseDatabase.getReference().child("publicForum").child(Calendar.getInstance().getTime().toString());
                databaseReferenc.child("from").setValue(userData.getName());
                databaseReferenc.child("title").setValue(title.getText().toString());
                databaseReferenc.child("description").setValue(description.getText().toString());
                databaseReferenc.child("time").setValue(Calendar.getInstance().getTime().toString());
                databaseReferenc.child("reply").setValue("No Reply Yet");
                title.setText("");
                description.setText("");
            }
        });


    }
}