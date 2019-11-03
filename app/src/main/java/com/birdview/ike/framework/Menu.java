package com.birdview.ike.framework;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

public class Menu extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tabLayout = (TabLayout) findViewById(R.id.menues);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new RealestateFragment(), "Realestate");
        adapter.addFragment(new HotelFragment(), "Hotels");
        adapter.addFragment(new FlightFragment(), "Flight");
        adapter.addFragment(new MovingFragment(), "Moving");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

}
