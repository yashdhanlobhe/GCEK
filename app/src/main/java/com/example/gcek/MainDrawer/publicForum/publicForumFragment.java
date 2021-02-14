package com.example.gcek.MainDrawer.publicForum;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gcek.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Map;

import static com.example.gcek.InitiateAppData.mfirebaseauth;


public class publicForumFragment extends Fragment implements PublicForumFirebaseAdapter.publicForumAdapterListner {

    ArrayList<PublicForumItemData> publicForumItemData;
    PublicForumAdapter publicForumAdapter;
    RecyclerView recyclerView;
    PublicForumFirebaseAdapter.publicForumAdapterListner publicForumAdapterListner;
    PublicForumFirebaseAdapter publicForumFirebaseAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_public_forum, container, false);
        recyclerView = root.findViewById(R.id.PublicForumRecyclerView);
        publicForumAdapterListner = this;



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        root.findViewById(R.id.floatingbuttontoaddpublicforum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext() , AddPublicForumActivity.class));
            }
        });
        initRecyclerView(root);
//        FirebaseDatabase.getInstance().getReference().child("publicForum").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                publicForumItemData = new ArrayList<>();
//                for(DataSnapshot db : snapshot.getChildren()){
//                    PublicForumItemData nd =db.getValue(PublicForumItemData.class);
//                    publicForumItemData.add(nd);
//                }
//
////                Collections.sort(publicForumItemData , new Sortbyroll());
//                publicForumAdapter = new PublicForumAdapter(publicForumItemData);
//                recyclerView.setAdapter(publicForumAdapter);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
        return  root ;

    }

    private void initRecyclerView(View root) {
        FirebaseFirestore.getInstance().collection("publicForumUpvotes").document(mfirebaseauth.getCurrentUser().getEmail()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String , Object> x = documentSnapshot.getData();
                startRecyclerView(x);
            }

            private void startRecyclerView(Map<String, Object> x) {
                Query query = FirebaseFirestore.getInstance()
                        .collection("PublicForum")
                        .orderBy("upvotes", Query.Direction.DESCENDING);
                FirestoreRecyclerOptions<PublicForumItemData> options = new FirestoreRecyclerOptions.Builder<PublicForumItemData>()
                        .setQuery(query, PublicForumItemData.class)
                        .build();
                publicForumFirebaseAdapter = new PublicForumFirebaseAdapter(options , x , publicForumAdapterListner);
                recyclerView.setAdapter(publicForumFirebaseAdapter);
                publicForumFirebaseAdapter.startListening();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if (publicForumFirebaseAdapter != null) {
            publicForumFirebaseAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (publicForumFirebaseAdapter != null) {
            publicForumFirebaseAdapter.stopListening();
        }
    }

    @Override
    public void makeToast(String toast) {
        Toast.makeText(getContext().getApplicationContext() , toast , Toast.LENGTH_SHORT).show();
    }
}