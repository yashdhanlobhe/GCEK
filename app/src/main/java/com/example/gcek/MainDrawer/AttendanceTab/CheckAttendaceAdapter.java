package com.example.gcek.MainDrawer.AttendanceTab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gcek.R;

import java.util.Map;

public class CheckAttendaceAdapter extends RecyclerView.Adapter<CheckAttendaceAdapter.mViewHoder> {
    Map<String, Object> totalAttendanceData, markedAttendace;

    public CheckAttendaceAdapter(Map<String, Object> totalAttendanceData, Map<String, Object> markedAttendace) {
        this.markedAttendace = markedAttendace;
        this.totalAttendanceData = totalAttendanceData;


    }

    @NonNull
    @Override
    public mViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendaceitemdata , parent , false);
        return new mViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHoder holder, int position) {
        String key = markedAttendace.keySet().toArray()[position].toString();
        holder.attendance.setText(markedAttendace.get(key).toString());
        holder.title.setText(key);
    }

    @Override
    public int getItemCount() {
        return totalAttendanceData.size();
    }

    class  mViewHoder extends RecyclerView.ViewHolder{
    TextView title , attendance ;
    public mViewHoder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.coursenameattendace);
        attendance = itemView.findViewById(R.id.courseattendance);
    }
}
}
