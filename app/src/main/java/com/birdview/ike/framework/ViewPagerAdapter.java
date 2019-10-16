package com.example.ko_run.hyphendevelopment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> lstFragment = new ArrayList<>();
    private final List<String> lstNames = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return lstFragment.get(i);
    }

    @Override
    public int getCount() {
        return lstNames.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lstNames.get(position);
    }
    public void addFragment(Fragment fragment, String names){
        lstFragment.add(fragment);
        lstNames.add(names);
    }
}
