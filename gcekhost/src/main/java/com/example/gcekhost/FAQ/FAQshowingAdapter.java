package com.example.gcekhost.FAQ;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gcekhost.R;

import java.util.List;

public class FAQshowingAdapter extends RecyclerView.Adapter<FAQshowingAdapter.ViewHolder> {

    public List<FAQData> faqDataList;
    public OnFAQClick onFAQClick;
    public  FAQshowingAdapter(List<FAQData> faqDataList , OnFAQClick onFAQClick){
        this.faqDataList = faqDataList;
        this.onFAQClick = onFAQClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faqlayout,parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(faqDataList.get(position).getTitle());
        holder.des.setText(faqDataList.get(position).getDescription());
        holder.reply.setText(faqDataList.get(position).getReply());
    }

    @Override
    public int getItemCount() {
        return faqDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title , des, reply ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.faqtitlelayout);
            des = itemView.findViewById(R.id.faqdescriptionlayout);
            reply = itemView.findViewById(R.id.faqanswerlayout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFAQClick.OnClickListner(faqDataList.get(getAdapterPosition()));
                }
            });
        }
    }
}
