package com.example.gcekhost.Notification;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gcekhost.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.Calendar;


public class AddNotification extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseDatabase database;
    StorageReference mStorageRef;
    DatabaseReference myRef;
    Button send_btn , seeNotifiacationBtn ,selectIMGbtn;
    Spinner spinner;
    ImageView imageView;
    EditText notification_title , notification_description;
    ProgressDialog pd;

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAAok9YObw:APA91bHgUXyZVXACilf2LCI0qb7SrBz22HLmIK0dzkw-GkO4i8rGGady1FSHy1Tm9NpW2t3C1dOHg_pJsguwWYrXFiel2mBbxWo5r0ChOlcdK-w6bg2xd3GgARyxxYkKA9vkx8YqNHE3";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC = "college";
    String NoticeClass;
    private static final int  PICKimg = 100;
    Uri uri ; String DownloadURI ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotifcation);
        Log.i("add notification" , "add");
        database = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Notices");
         pd= new ProgressDialog(this);

        send_btn = (Button)findViewById(R.id.send_btn_notifiacaiton);
        seeNotifiacationBtn = (Button)findViewById(R.id.see_notifiaction_btn);
        imageView = findViewById(R.id.uploadingimage);
        selectIMGbtn = findViewById(R.id.imageselect);
        notification_title = (EditText) findViewById(R.id.title_notification);
        notification_description=(EditText)findViewById(R.id.description_notification);
        spinner = (Spinner) findViewById(R.id.spinnernotices);

        ArrayAdapter<CharSequence> spinneradapter = ArrayAdapter.createFromResource(this, R.array.notices, android.R.layout.simple_spinner_item);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(spinneradapter);


        selectIMGbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =Calendar.getInstance().getTime().toString();
                uploadImage(id);
                myRef = database.getReference().child("Notices").child(NoticeClass).child(id);
                myRef.child("title").setValue(notification_title.getText().toString());
                myRef.child("description").setValue(notification_description.getText().toString());
                myRef.child("id").setValue(id);
                myRef.child("noticeURI").setValue(DownloadURI);

            }
        });
        seeNotifiacationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Notification.class));
            }
        });
    }

    private void uploadImage(String name ) {
        StorageReference mStorage = mStorageRef.child(""+NoticeClass+"/" + name + ".jpg");
//        UploadTask uploadTask = mStorage.putFile(uri);
        
        pd.show();

        mStorage.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        mStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                DownloadURI = uri.toString();
                                Toast.makeText(getApplicationContext() , uri.toString() , Toast.LENGTH_LONG).toString();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
            pd.dismiss();
    }


    private void OpenGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery , PICKimg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICKimg && requestCode == PICKimg){
            uri = data.getData();
            imageView.setImageURI(uri);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        NoticeClass = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        NoticeClass = "college";
    }
}


