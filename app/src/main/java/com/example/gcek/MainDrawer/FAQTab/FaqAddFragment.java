package com.example.gcek.MainDrawer.FAQTab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gcek.MainActivityWithLogin;
import com.example.gcek.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static com.example.gcek.MainActivityWithLogin.userData;

public class FaqAddFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_faq_add, container, false);
        EditText des = root.findViewById(R.id.adddesfaq);
        EditText title = root.findViewById(R.id.addtitlefaq);
        String date  = Calendar.getInstance().getTime().toString();
        root.findViewById(R.id.addfaqbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                        .child("faq")
                        .child("unanswered")
                        .child(date);

                databaseReference.child("title").setValue(title.getText().toString());
                databaseReference.child("description").setValue(des.getText().toString());
                databaseReference.child("date").setValue(date);
                databaseReference.child("GcekId").setValue(userData.getGCEKID());
                databaseReference.child("Reply").setValue("No reply yet");

                Toast.makeText(getContext() , "Added FAQ successfully" , Toast.LENGTH_SHORT).show();
                ((MainActivityWithLogin) getActivity()).replaceFragment(new MainFAQFragment());
            }
        });
        return root;
    }
}