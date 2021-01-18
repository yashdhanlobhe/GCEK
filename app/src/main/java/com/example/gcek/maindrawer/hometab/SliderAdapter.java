package com.example.gcek.maindrawer.hometab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.gcek.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class SliderAdapter extends PagerAdapter {
    List<Integer> listImages;
    Context context ;
    LayoutInflater layoutInflater;

    public SliderAdapter(List<Integer> listImages, Context context) {
        this.listImages = listImages;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listImages.size();
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
        ImageView imageView = (ImageView)view.findViewById(R.id.homeviewpagerimageview);
        imageView.setImageResource(listImages.get(position));
        container.addView(view);
        return  view;
    }
}
