package com.example.gcek.MainDrawer.publicForum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gcek.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static com.example.gcek.InitiateAppData.mfirebaseauth;
import static com.example.gcek.MainActivityWithLogin.userData;
import static com.example.gcek.MainActivityWithLogin.userImage;

public class PublicForumFirebaseAdapter extends FirestoreRecyclerAdapter<PublicForumItemData ,PublicForumFirebaseAdapter.PublicForumViewHoder > {
//imp Note === here we have getting all arraylist of public forum and displaying it in long run we have to change this type to only some items in public forum which are shown on screen

    Map<String, Object> x;
    PublicForumFirebaseAdapter.publicForumAdapterListner publicForumAdapterListner;

    public PublicForumFirebaseAdapter(@NonNull FirestoreRecyclerOptions<PublicForumItemData> options, Map<String, Object> x, publicForumAdapterListner publicForumAdapterListner) {
        super(options);
        this.x = x;
        this.publicForumAdapterListner = publicForumAdapterListner;
    }

    @Override
    protected void onBindViewHolder(@NonNull PublicForumViewHoder holder, int position, @NonNull PublicForumItemData model) {
        holder.title.setText(model.title);
        holder.description.setText(model.description);
        holder.reply.setText(model.reply);
        holder.from.setText(model.from);
        holder.upvotes.setText(Integer.toString(model.upvotes));
        holder.tag.setText(model.tag);
        if(x.containsKey(model.time)){
            holder.upvoteimg.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24_light);
        }
    }

    @NonNull
    @Override
    public PublicForumViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PublicForumFirebaseAdapter.PublicForumViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.public_forum_item_layout , parent , false));
    }

    class PublicForumViewHoder extends RecyclerView.ViewHolder{
        TextView title , description , reply , from , upvotes , tag;
        ImageView upvoteimg;
        public PublicForumViewHoder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TitleCardviewPublicForum);
            description = itemView.findViewById(R.id.publicForumDescriptionInCardView);
            reply = itemView.findViewById(R.id.publicForumReplyInCardView);
            from = itemView.findViewById(R.id.publicForumfromInCardView);
            upvoteimg = itemView.findViewById(R.id.uparrowimagepublicforum);
            upvotes = itemView.findViewById(R.id.numberofupvotescardviewpublicforum);
            tag = itemView.findViewById(R.id.publicforumtagtextview);
            upvoteimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    HashMap<String , String> update = new HashMap<>();
                    update.put(getItem(getAdapterPosition()).time , "+1");
                    AddUpvote(update);
                }

                private void AddUpvote(HashMap<String, String> update) {
                    if (!x.containsKey(getItem(getAdapterPosition()).time)){
                        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(getAdapterPosition());
                        documentSnapshot.getReference().update("upvotes" ,getItem(getAdapterPosition()).upvotes+1 );
                        x.put(getItem(getAdapterPosition()).time , "+1");
                        FirebaseFirestore.getInstance().collection("publicForumUpvotes").document(mfirebaseauth.getCurrentUser().getEmail()).set(update , SetOptions.merge());
                    }
                    else {
                        publicForumAdapterListner.makeToast("Already Upvoted !");
                    }
                 }
            });
        }
    }
    interface publicForumAdapterListner {
        public void makeToast(String toast);

    }
}
