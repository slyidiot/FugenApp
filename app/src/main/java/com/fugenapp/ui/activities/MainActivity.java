package com.fugenapp.ui.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.fugenapp.R;
import com.fugenapp.ui.view.FadingImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int index = 0;
    FadingImageView imageView;
    Timer timer;

    int[] pics = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5,
            R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler mHandler = new Handler();
        imageView = findViewById(R.id.slideshow_view);

        imageView.setEdgeLength(150);
        imageView.setFadeDirection(FadingImageView.FadeSide.TOP_SIDE);

        final Runnable start = new Runnable() {
            public void run() {
                imageView.setImageResource(pics[index % pics.length]);
                index++;
                Animation fadein = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in);
                imageView.startAnimation(fadein);
            }
        };

        int delay = 3000;
        int period = 3000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                mHandler.post(start);
            }
        }, delay, period);
    }

    public void showEvents(View view) {
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void showTrailer(View view) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + getString(R.string.youtube_video_id)));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + getString(R.string.youtube_video_id)));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }

    }
}
