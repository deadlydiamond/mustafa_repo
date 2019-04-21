package com.example.seekm.uitrial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class OnBoarding_1 extends AppCompatActivity {
    private FloatingActionButton mNext;

    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;

    private SliderAdapter sliderAdapter;

    private ViewPager.OnPageChangeListener pageChangedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_1);

        mViewPager = findViewById(R.id.slideViewPager);

        sliderAdapter = new SliderAdapter(this);
        mViewPager.setAdapter(sliderAdapter);

        DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);
        dotsIndicator.setViewPager(mViewPager);

        mNext = findViewById(R.id.floatingActionButton2);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity(OnBoarding_1.this, MobileV.class);
            }
        });
    }
    void startNewActivity(Activity one, Class two) {
        Intent intent = new Intent(one, two);
        startActivity(intent);
    }
}
