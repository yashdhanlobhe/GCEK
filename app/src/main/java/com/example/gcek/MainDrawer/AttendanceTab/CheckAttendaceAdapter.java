package com.example.gcek.MainDrawer.AttendanceTab;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gcek.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class CheckAttendaceAdapter extends RecyclerView.Adapter<CheckAttendaceAdapter.mViewHoder> {
    Map<String, Object>  markedAttendace;

    public CheckAttendaceAdapter( Map<String, Object> markedAttendace) {
        this.markedAttendace = markedAttendace;

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
        String value = markedAttendace.get(key).toString();

        if (value.equals("0")){
            value = "000000";
        }else if (value.length()==4){
            value="00"+value;
        }else if (value.length()==5){
            value = "0" +value;
        }

        Log.d("xyz" , markedAttendace.toString());
        Log.d("xyz" , value);

        Float totalattended = Float.parseFloat(value.substring(3,6)) ;
        Float total =  Float.parseFloat(value.substring(0,3));

        Float per = (totalattended/total)*100;
        String formattedPersentage = String.format("%.0f", per);

        if(total == 0.0){
            formattedPersentage="100";
        }
        holder.title.setText(key);
        holder.attendance.setText(String.format("%.0f", totalattended));
        holder.total.setText(String.format("%.0f", total));
        holder.persentage.setText(formattedPersentage + "%");

    }

    @Override
    public int getItemCount() {
        return markedAttendace.size();
    }

    class  mViewHoder extends RecyclerView.ViewHolder{
    TextView title , attendance , total , persentage ;
    public mViewHoder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.coursenameattendace);
        attendance = itemView.findViewById(R.id.courseattendedtextview);
        total = itemView.findViewById(R.id.totalattendedtextview);
        persentage = itemView.findViewById(R.id.attendacepersentagetextview);
    }
}
}
