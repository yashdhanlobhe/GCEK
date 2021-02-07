package com.example.gcek.MainDrawer.publicForum;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gcek.R;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    public int getItemCount() {
        return publicForumItemData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title , description , reply , from;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TitleCardviewPublicForum);
            description = itemView.findViewById(R.id.publicForumDescriptionInCardView);
            reply = itemView.findViewById(R.id.publicForumReplyInCardView);
            from = itemView.findViewById(R.id.publicForumfromInCardView);
        }
    }
}
