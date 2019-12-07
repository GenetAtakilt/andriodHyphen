package com.birdview.ike.main;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainViewPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> lstFragment = new ArrayList<>();
    private final List<String> lstNames = new ArrayList<>();

    public MainViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return lstFragment.get(position);
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

    public void addsFragment(Fragment fragment, String names){
        lstFragment.add(fragment);
        lstNames.add(names);
    }

}
