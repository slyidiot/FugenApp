package com.fugenapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fugenapp.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();
    }

    private void findViews() {
    }
}
