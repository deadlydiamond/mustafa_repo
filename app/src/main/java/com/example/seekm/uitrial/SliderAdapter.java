package com.example.seekm.uitrial;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    public int[] slide_images = {
            R.drawable.location,
            R.drawable.dollar,
            R.drawable.ui
    };
    public int[] slide_backgrounds = {
            R.drawable.splash_1,
            R.drawable.splash_2,
            R.drawable.splash_3
    };
    public String[] slide_headings = {
            "NEARBY",
            "NO REGISTRATION FEE",
            "EASY TO USE"
    };
    public String[] slide_desc = {
            "Find your nearest students on the go, start by filling out the required information and wait for the student's request",
            "Get yourself registered for completely free, we do not charge commission or hidden charges.",
            "Our app is simple and easy to use, as soon as you start you get used to it."
    };
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull View container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, (ViewGroup) container, false);

        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView slideHeading = view.findViewById(R.id.slide_heading);
        TextView slideDesc = view.findViewById(R.id.slide_desc);
        RelativeLayout relativeLayout = view.findViewById(R.id.relativeLayout);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDesc.setText(slide_desc[position]);
        relativeLayout.setBackgroundResource(slide_backgrounds[position]);

        ((ViewGroup) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
