package com.example.gcek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowPersonalNoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notice);

        String from = getIntent().getStringExtra("from");
        String title = getIntent().getStringExtra("title");
        String message = getIntent().getStringExtra("message");

        TextView fromTv = findViewById(R.id.fromtextview);
        TextView titleTv = findViewById(R.id.titletextview);
        TextView messageTv = findViewById(R.id.messagetextview);

        fromTv.setText(from);
        titleTv.setText(title);
        messageTv.setText(message);


    }
}