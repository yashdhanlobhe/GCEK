package com.example.gcekhost.functions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gcekhost.R;

import static com.example.gcekhost.serviceNotifiaciton.SendNotifiaction.SendNotifiacionToDevices;

public class SendPrivateNotifiaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_private_notifiaction);
        Context mcontext  = this;
        EditText topic = findViewById(R.id.topicprivatemessage);
        EditText title = findViewById(R.id.titleprivatemessage);
        EditText des = findViewById(R.id.descprivatemessage);
        EditText from = findViewById(R.id.fromprivatemessage);
        findViewById(R.id.sendprivatenotifiactionbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendNotifiacionToDevices(mcontext ,topic.getText().toString() , from.getText().toString() ,title.getText().toString() ,des.getText().toString()  );
            }
        });
    }
}