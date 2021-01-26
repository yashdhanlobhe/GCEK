package com.example.gcekhost.FAQ;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.gcekhost.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FAQunanswerd extends AppCompatActivity implements OnFAQClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_qunanswerd);
        ProgressDialog pd = new ProgressDialog(this);
        OnFAQClick onFAQClick = this;

        pd.setCancelable(false);
        pd.setTitle("Loading...");
        pd.show();
        Context mcontext = this;

        RecyclerView recyclerView = findViewById(R.id.faqrecycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
        recyclerView.setLayoutManager(linearLayoutManager);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("faq").child("unanswered").getRef();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<FAQData> faqData  = new ArrayList<>();
                for (DataSnapshot db : snapshot.getChildren()){
                    FAQData faqDataclass = db.getValue(FAQData.class);
                    faqData.add(faqDataclass);
                }
                recyclerView.setAdapter(new FAQshowingAdapter(faqData, onFAQClick));
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pd.dismiss();
            }
        });
    }

    @Override
    public void OnClickListner(FAQData nd) {
        startActivity(new Intent(getBaseContext() , answerFAQ.class)
                .putExtra("GcekId", nd.getGcekId())
                .putExtra("date", nd.getDate())
                .putExtra("description", nd.getDescription())
                .putExtra("title", nd.getTitle()));
    }

    @Override
    public void OnLongClickListner(FAQData nd) {

    }
}
