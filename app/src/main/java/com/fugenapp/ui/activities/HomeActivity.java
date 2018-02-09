package com.fugenapp.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fugenapp.R;
import com.fugenapp.adapters.EventFragmentsAdapter;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView flagshipBtn;
    private TextView eyeCatcherBtn;
    private TextView technicalBtn;

    private ViewPager fragmentPager;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();
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
}