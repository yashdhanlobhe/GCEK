package com.example.gcek.TPO;

import android.widget.ImageView;

public class SliderItem {
    int image;
    String text;

    public SliderItem() {
    }

    public SliderItem(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
