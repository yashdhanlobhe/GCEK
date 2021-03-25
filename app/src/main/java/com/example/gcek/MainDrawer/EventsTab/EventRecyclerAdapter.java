package com.example.gcek.MainDrawer.EventsTab;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gcek.EventDescription;
import com.example.gcek.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventRecyclerAdapter extends  RecyclerView.Adapter<EventRecyclerAdapter.EventRecyclerAdapterHolder> {
    ArrayList<EventData> data ;

    public EventRecyclerAdapter(ArrayList<EventData> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public EventRecyclerAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventRecyclerAdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.event_recycler_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventRecyclerAdapterHolder holder, int position) {
        Picasso.get().load(data.get(position).uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (data != null){
            Log.d("yd7" , data.toString());
            return data.size();
        }
        else
        {
            return 0;
        }
    }

    public class EventRecyclerAdapterHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        public EventRecyclerAdapterHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.event_item_image_view);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext() , EventDescription.class).putExtra("title" , data.get(getAdapterPosition()).title));
                }
            });
        }
    }
}
