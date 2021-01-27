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

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        List<SliderItem> sliderItems = new ArrayList<>();
        SliderItem s1 = new SliderItem()

        SliderView sliderView = findViewById(R.id.imageSlidertpo);
        SliderAdapter adapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(adapter);
    }
}