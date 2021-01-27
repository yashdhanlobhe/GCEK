package com.example.gcek.TPO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.gcek.R;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class tpomain extends AppCompatActivity {
    public Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpomain);

        toolbar = findViewById(R.id.tpotoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setTitle("TPO");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.tpoposter1 , "Placement" ));
        sliderItems.add(new SliderItem(R.drawable.tpoposter2 , "Placement" ));
        sliderItems.add(new SliderItem(R.drawable.tpoposter3 , "Placement" ));
        sliderItems.add(new SliderItem(R.drawable.tpoposter4 , "Placement" ));
        sliderItems.add(new SliderItem(R.drawable.tpoposter5 , "Placement" ));


        SliderView sliderView = findViewById(R.id.imageSlidertpo);
        SliderAdapter adapter = new SliderAdapter(this , sliderItems);
        sliderView.setSliderAdapter(adapter);
        sliderView.startAutoCycle();
        sliderView.setSliderAnimationDuration(1000);
    }
}