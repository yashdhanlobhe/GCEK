package com.example.gcek.maindrawer.hometab;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.text.PrecomputedText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.gcek.AppData;
import com.example.gcek.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import javax.xml.transform.Result;

import static com.example.gcek.AppData.PosterBitmapList;


public class SliderAdapter extends PagerAdapter {
    List<PosterData> posterData;
    Context context ;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<PosterData> posterData, Context context) {
        this.posterData = posterData;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return posterData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.carditemhometabviewpager,container,false);
        ImageView imageView = view.findViewById(R.id.homeviewpagerimageview);
        TextView textView = view.findViewById(R.id.homeviewpagerTextview);
        textView.setText(posterData.get(position).getTitle());
        try{
            imageView.setImageBitmap(PosterBitmapList.get(position));
        }catch (Exception e){

        }
        container.addView(view);
        return  view;
    }

    private class SetImage extends AsyncTask<Void, Void, Void> {
        String uriImage;
        ImageView imageView;
        Bitmap DownloadedImage;
        int position;
        public SetImage(String noticeURI, ImageView imageView , int Position) {
            this.uriImage = noticeURI ;
            this.imageView = imageView;
            this.position = Position;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                DownloadedImage = Picasso.get().load(posterData.get(position).getNoticeURI()).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            imageView.setImageBitmap(DownloadedImage);
        }
    }
}
