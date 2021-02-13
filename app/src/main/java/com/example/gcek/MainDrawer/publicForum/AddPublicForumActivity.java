package com.example.gcek.MainDrawer.publicForum;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gcek.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;


import static com.example.gcek.MainActivityWithLogin.userData;

public class AddPublicForumActivity extends AppCompatActivity {
    Spinner tagspinner;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_public_forum);
        mcontext = this;

        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Adding");
        pd.setCancelable(false);
        TextView title = findViewById(R.id.titlepublicForumADD);
        TextView description = findViewById(R.id.DescriptionpublicForumADD);

        tagspinner = findViewById(R.id.tagspinnerpublicforum);
        ArrayAdapter<CharSequence> batchSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.TagForPublicForum, android.R.layout.simple_spinner_item);
        batchSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagspinner.setAdapter(batchSpinnerAdapter);

        Button btn = findViewById(R.id.addpublicforumbutton);
                btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().equals("")){
                    title.setFocusableInTouchMode(true);
                    Toast.makeText(mcontext , "Please fill this tab" , Toast.LENGTH_SHORT).show();
                }else if (description.getText().toString().equals("")){
                    description.setFocusableInTouchMode(true);
                    Toast.makeText(mcontext , "Please fill this tab" , Toast.LENGTH_SHORT).show();
                }else {
                String Id = Calendar.getInstance().getTime().toString();
                PublicForumItemData data = new PublicForumItemData(userData.getName(),title.getText().toString(),description.getText().toString(),"No Reply Yet", Id, 0, tagspinner.getSelectedItem().toString());
//                FirebaseDatabase.getInstance().getReference().child("publicForum").child(Id).setValue(data);
                    FirebaseFirestore.getInstance().collection("PublicForum").document(Id).set(data);
                onBackPressed();
            }
            }
        });
    }
}