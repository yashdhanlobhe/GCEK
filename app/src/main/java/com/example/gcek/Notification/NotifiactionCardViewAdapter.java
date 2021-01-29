package com.example.gcek.Notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gcek.R;

import java.util.List;


public class NotifiactionCardViewAdapter extends  RecyclerView.Adapter<NotifiactionCardViewAdapter.ViewHolder> {
    public List<NotificationDataClass> notificationlist;
    public OnNotifiacaitonClick onNotifiacaitonClick;

    public NotifiactionCardViewAdapter(List<NotificationDataClass> notificationlist, OnNotifiacaitonClick onNotifiacaitonClick) {
        this.notificationlist = notificationlist;
        this.onNotifiacaitonClick = onNotifiacaitonClick;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.relativelayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationDataClass notification = notificationlist.get(position);
        holder.title.setText(notification.title);
        holder.description.setText(notification.description);
        holder.time.setText(getDateOfnotification(notification.id));

    }

    private String getDateOfnotification(String id) {
        String date = null;
        String dateArray[] = id.split(" ");
        date = dateArray[1]+ " " + dateArray[2]+" " + dateArray[0];
        return date;
    }

    @Override
    public int getItemCount() {
        return notificationlist.size();
    }

   class ViewHolder extends RecyclerView.ViewHolder {
    public TextView title , time;
    public TextView description;
    public CardView mylayout;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.notificaitontitle);
        description = (TextView)itemView.findViewById(R.id.notificationdescription);
        mylayout = (CardView) itemView.findViewById(R.id.mylayout);
        time = (TextView)itemView.findViewById(R.id.NotificationDateText);
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
