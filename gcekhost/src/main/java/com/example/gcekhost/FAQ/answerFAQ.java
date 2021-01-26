package com.example.gcekhost.FAQ;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gcekhost.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class answerFAQ extends AppCompatActivity {
    String topic , title , id , des , reply , date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_f_a_q);
        ProgressDialog pd = new ProgressDialog(this);
        pd.setCancelable(false);

        title = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("GcekId");
        des = getIntent().getStringExtra("description");
        date = getIntent().getStringExtra("date");

        TextView tit = findViewById(R.id.answerfaqtitle);
        TextView desc = findViewById(R.id.answerfaqdes);
        EditText editText = findViewById(R.id.answerforfaq);


        tit.setText(title);
        desc.setText(des);

        Spinner batchSpinner = findViewById(R.id.answerfaqspinner);
        ArrayAdapter<CharSequence> batchSpinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext() , R.array.faqtype, android.R.layout.simple_spinner_item);
        batchSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        batchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                topic = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        batchSpinner.setAdapter(batchSpinnerAdapter);

        findViewById(R.id.answerreplybtnforfaq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                reply = editText.getText().toString();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("faq").child(topic).child(date).getRef();
                databaseReference.child("title").setValue(title);
                databaseReference.child("GcekId").setValue(id);
                databaseReference.child("description").setValue(des);
                databaseReference.child("date").setValue(date);
                databaseReference.child("Reply").setValue(reply);

                FirebaseDatabase.getInstance().getReference().child("faq").child("unanswered").child(date).removeValue();
                pd.dismiss();

                Toast.makeText(getBaseContext() , "Reply successful" , Toast.LENGTH_SHORT);
                startActivity(new Intent(getApplicationContext() , FAQunanswerd.class));
            }
        });
    }
}