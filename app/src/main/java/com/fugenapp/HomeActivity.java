package com.fugenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class HomeActivity extends AppCompatActivity implements SpaceOnClickListener {

    private SpaceNavigationView navigationView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        navigationView.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViews();
        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.showTextOnly();
        navigationView.setSpaceOnClickListener(this);
        navigationView.addSpaceItem(new SpaceItem("EVENTS", R.drawable.ic_add_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("PROFILE", R.drawable.ic_add_black_24dp));
    }

    private void findViews() {
        navigationView = (SpaceNavigationView) findViewById(R.id.navigation_view);
    }

    @Override
    public void onCentreButtonClick() {
        Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int itemIndex, String itemName) {
        Toast.makeText(this, itemName+" clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemReselected(int itemIndex, String itemName) {
        Toast.makeText(this, itemName+" clicked again", Toast.LENGTH_SHORT).show();
    }
}
