package com.birdview.ike.framework;



import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> lstFragment = new ArrayList<>();
    private final List<String> lstNames = new ArrayList<>();

    public MViewPagerAdapter(FragmentManager fm) {
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
    public void addsFragment(Fragment fragment, String names){
        lstFragment.add(fragment);
        lstNames.add(names);
    }
}
