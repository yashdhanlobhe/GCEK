package com.example.gcek.MainDrawer.publicForum;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gcek.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static com.example.gcek.InitiateAppData.mfirebasestorage;
import static com.example.gcek.MainActivityWithLogin.userData;

public class PublicForumAdapter extends RecyclerView.Adapter<PublicForumAdapter.ViewHolder> {

    ArrayList<PublicForumItemData> publicForumItemData;

    public PublicForumAdapter(ArrayList<PublicForumItemData> publicForumItemData) {
        this.publicForumItemData = publicForumItemData;
    }

    @NonNull
    @Override
    public PublicForumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.public_forum_item_layout , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull PublicForumAdapter.ViewHolder holder, int position) {
        PublicForumItemData currentitem = publicForumItemData.get(position) ;
        holder.title.setText(currentitem.title);
        holder.description.setText(currentitem.description);
        holder.reply.setText(currentitem.reply);
        holder.from.setText(currentitem.from);
        holder.upvotes.setText(Integer.toString(currentitem.upvotes));
        holder.tag.setText(currentitem.tag);
    }

    @Override
    public int getItemCount() {
        return publicForumItemData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title , description , reply , from , upvotes , tag;
        ImageView upvoteimg;
        public ViewHolder(@NonNull View itemView) {
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
                    cheackUpvoteInImage(publicForumItemData.get(getAdapterPosition()));
                }

                private void cheackUpvoteInImage(PublicForumItemData publicForumItemData) {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("publicForumUpvotes").child("upvotes")
                            .child(publicForumItemData.time).child(userData.getName()).getRef();

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.getValue() == null){
                                ref.setValue("true");
                                DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("publicForum").child(publicForumItemData.time).child("upvotes").getRef();
                                ref2.setValue(publicForumItemData.upvotes+1);
                                return;
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            });
        }
    }
}
