package com.example.gcekhost.functions;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gcekhost.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static com.example.gcekhost.serviceNotifiaciton.SendNotifiaction.SendNotifiacionToDevices;

public class SendPrivateNotifiaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_private_notifiaction);
        Context mcontext  = this;
        ProgressDialog pd= new ProgressDialog(mcontext);
        pd.setCancelable(false);

        EditText topic = findViewById(R.id.topicprivatemessage);
        EditText title = findViewById(R.id.titleprivatemessage);
        EditText des = findViewById(R.id.descprivatemessage);
        EditText from = findViewById(R.id.fromprivatemessage);
        findViewById(R.id.sendprivatenotifiactionbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                SendNotifiacionToDevices(mcontext , topic.getText().toString() , from.getText().toString() ,title.getText().toString() ,des.getText().toString());
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("privatemessage").child(topic.getText().toString()).child(Calendar.getInstance().getTime().toString());
                myRef.child("title").setValue(title.getText().toString());
                myRef.child("description").setValue(des.getText().toString());
                myRef.child("id").setValue(Calendar.getInstance().getTime().toString());
                myRef.child("noticeURI").setValue(null);
                pd.dismiss();
            }
        });
    }
}