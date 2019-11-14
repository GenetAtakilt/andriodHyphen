package com.birdview.hyphen.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.birdview.hyphen.fragments.DashboardFragment;
import com.birdview.hyphen.fragments.HomeFragment;
import com.birdview.hyphen.fragments.NotificationFragment;
import com.birdview.hyphen.fragments.ProfileFragment;

public class BottomNavPagerAdapter extends FragmentPagerAdapter {
    public BottomNavPagerAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DashboardFragment.newInstance();
            case 1:
                return HomeFragment.newInstance();
            case 2:
                return NotificationFragment.newInstance();
            case 3:
                return ProfileFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}

