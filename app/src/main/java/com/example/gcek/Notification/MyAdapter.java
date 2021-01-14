package com.example.gcek.Notification;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gcek.R;

import java.util.List;


public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public List<NotificationData> notificationlist;
    public OnNotifiacaitonClick onNotifiacaitonClick;

    public MyAdapter(List<NotificationData> notificationlist, OnNotifiacaitonClick onNotifiacaitonClick) {
        this.notificationlist = notificationlist;
        this.onNotifiacaitonClick = onNotifiacaitonClick;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.relativelayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationData notification = notificationlist.get(position);
        holder.title.setText(notification.title);
        holder.description.setText(notification.description);

    }

    @Override
    public int getItemCount() {
        return notificationlist.size();
    }

   class ViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView description;
    public CardView mylayout;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.notificaitontitle);
        description = (TextView)itemView.findViewById(R.id.notificationdescription);
        mylayout = (CardView) itemView.findViewById(R.id.mylayout);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNotifiacaitonClick.OnClickListner(notificationlist.get(getAdapterPosition()));
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onNotifiacaitonClick.OnLongClickListner(notificationlist.get(getAdapterPosition()));
                return false;
            }
        });
    }
}
}
