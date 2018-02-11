package com.fugenapp.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.fugenapp.R;
import com.fugenapp.adapters.EventFragmentsAdapter;
import com.fugenapp.interfaces.OnEventSelectedListener;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, OnEventSelectedListener {

    private TextView flagshipBtn;
    private TextView eyeCatcherBtn;
    private TextView technicalBtn;
    private ImageView poster;
    private ViewPager fragmentPager;

    private AlphaAnimation fadeIn;
    private AlphaAnimation fadeOut;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();

        fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(100);
        fadeIn.setFillAfter(true);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                poster.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setDuration(100);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                poster.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fragmentPager.setAdapter(new EventFragmentsAdapter(getSupportFragmentManager()));
        fragmentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                refreshCategoryButtons(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void findViews() {
        fragmentPager = findViewById(R.id.fragment_pager);
        flagshipBtn = findViewById(R.id.flagship_btn);
        eyeCatcherBtn = findViewById(R.id.eye_catcher_btn);
        technicalBtn = findViewById(R.id.technical_btn);
        poster = findViewById(R.id.event_poster);
    }

    private void refreshCategoryButtons(int position) {
        switch (position) {
            case 0:
                flagshipBtn.setBackground(getResources().getDrawable(R.drawable.blue_btn_bg));
                eyeCatcherBtn.setBackground(getResources().getDrawable(R.drawable.white_btn_bg));
                technicalBtn.setBackground(getResources().getDrawable(R.drawable.white_btn_bg));
                flagshipBtn.setTextColor(getResources().getColor(R.color.white));
                eyeCatcherBtn.setTextColor(getResources().getColor(R.color.black));
                technicalBtn.setTextColor(getResources().getColor(R.color.black));
                break;
            case 1:
                flagshipBtn.setBackground(getResources().getDrawable(R.drawable.white_btn_bg));
                eyeCatcherBtn.setBackground(getResources().getDrawable(R.drawable.blue_btn_bg));
                technicalBtn.setBackground(getResources().getDrawable(R.drawable.white_btn_bg));
                flagshipBtn.setTextColor(getResources().getColor(R.color.black));
                eyeCatcherBtn.setTextColor(getResources().getColor(R.color.white));
                technicalBtn.setTextColor(getResources().getColor(R.color.black));
                break;
            case 2:
                flagshipBtn.setBackground(getResources().getDrawable(R.drawable.white_btn_bg));
                eyeCatcherBtn.setBackground(getResources().getDrawable(R.drawable.white_btn_bg));
                technicalBtn.setBackground(getResources().getDrawable(R.drawable.blue_btn_bg));
                flagshipBtn.setTextColor(getResources().getColor(R.color.black));
                eyeCatcherBtn.setTextColor(getResources().getColor(R.color.black));
                technicalBtn.setTextColor(getResources().getColor(R.color.white));
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item0:
                fragmentPager.setCurrentItem(0, true);
                refreshCategoryButtons(0);
                break;
            case R.id.item1:
                fragmentPager.setCurrentItem(1, true);
                refreshCategoryButtons(1);
                break;
            case R.id.item2:
                fragmentPager.setCurrentItem(2, true);
                refreshCategoryButtons(2);
                break;
        }
    }

    @Override
    public void onEventSelected(int resID) {
        //poster.setImageResource(resID);
        //poster.setVisibility(View.VISIBLE);
        //poster.startAnimation(fadeIn);
    }

    @Override
    public void onEventDeselected() {
        //poster.startAnimation(fadeOut);
        //poster.setVisibility(View.INVISIBLE);
    }
}