package com.birdview.ike.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.birdview.ike.framework.R;
import com.birdview.ike.main.MainViewPageAdapter;
import com.birdview.ike.main.home.flight.flightSearch.FlightSearchFragment;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainViewPageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        adapter = new MainViewPageAdapter(getChildFragmentManager());

        adapter.addsFragment(new FlightSearchFragment(), "Realestate");
//        adapter.addsFragment(new HotelFragment(), "Hotels");
//        adapter.addsFragment(new FlightFragment(), "Flight");
//        adapter.addsFragment(new MovingFragment(), "Moving");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}