package com.example.gcekhost.Attendace;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gcekhost.R;

public class ChooseClass extends AppCompatActivity {
    Spinner year , branch , subject;
    Context mcontext ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_class);
        mcontext = this;
        EditText start = findViewById(R.id.stratingattendancerollno);
        EditText end = findViewById(R.id.endingattendancerollno);

        branch = findViewById(R.id.choosebranchattendace);
        ArrayAdapter<CharSequence> branchSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.Branches, android.R.layout.simple_spinner_item);
        branchSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(branchSpinnerAdapter);

        year =findViewById(R.id.chooseyearattendace);
        ArrayAdapter<CharSequence> batchSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.Year, android.R.layout.simple_spinner_item);
        batchSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(batchSpinnerAdapter);

        subject =findViewById(R.id.choosesubjectattendace);
        ArrayAdapter<CharSequence> subspinner = ArrayAdapter.createFromResource(this, R.array.FySubject, android.R.layout.simple_spinner_item);
        subspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject.setAdapter(subspinner);


        findViewById(R.id.starttakingattancebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mcontext ,TakeAttendaceActivity.class)
                        .putExtra("year",year.getSelectedItem().toString())
                        .putExtra("branch",branch.getSelectedItem().toString())
                        .putExtra("subject",subject.getSelectedItem().toString())
                        .putExtra("start",start.getText().toString())
                        .putExtra("end" , end.getText().toString()));
            }
        });



    }
}