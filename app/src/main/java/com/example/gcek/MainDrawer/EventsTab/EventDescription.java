package com.example.gcek.MainDrawer.EventsTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gcek.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class EventDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);
        overridePendingTransition(R.anim.slide_in_from_right , R.anim.slide_out_to_left);
        Toolbar toolbar = findViewById(R.id.EventDescToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String title = getIntent().getStringExtra("title");

        FirebaseFirestore.getInstance().collection("Events").document(title).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String , Object> value = documentSnapshot.getData();
                TextView title = findViewById(R.id.EventTitleDesc);
                TextView desc = findViewById(R.id.EventDescDesc);
                ImageView imageView = findViewById(R.id.imageViewEventSub);

                title.setText(value.get("title").toString());
                desc.setText(value.get("des").toString());
                Picasso.get().load(value.get("subPoster").toString()).into(imageView);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left , R.anim.slide_out_to_right);
    }
}