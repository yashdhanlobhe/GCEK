package com.example.gcek.maindrawer.faqfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.gcek.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FAQShowingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q_showing);
        ProgressDialog pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setTitle("Loading...");
        pd.show();
        Context mcontext = this;
        RecyclerView recyclerView = findViewById(R.id.faqrecycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
        recyclerView.setLayoutManager(linearLayoutManager);

        String type = getIntent().getStringExtra("name");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("faq").child(type).getRef();

        Toolbar toolbar = findViewById(R.id.faqshowatoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<FAQData> faqData  = new ArrayList<>();
                for (DataSnapshot db : snapshot.getChildren()){
                    FAQData faqDataclass = db.getValue(FAQData.class);
                    faqData.add(faqDataclass);
                }
                recyclerView.setAdapter(new FAQshowingAdapter(faqData));
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pd.dismiss();
            }
        });
    }
}