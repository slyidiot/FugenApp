package com.fugenapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fugenapp.ui.fragments.EyeCatchersFragment;
import com.fugenapp.ui.fragments.FlagshipEventsFragment;
import com.fugenapp.ui.fragments.TechnicalEventsFragment;

public class EventFragmentsAdapter extends FragmentPagerAdapter {

    public EventFragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FlagshipEventsFragment();
            case 1:
                return new EyeCatchersFragment();
            case 2:
                return new TechnicalEventsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}