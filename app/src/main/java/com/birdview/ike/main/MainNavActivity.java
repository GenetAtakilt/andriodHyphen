package com.birdview.ike.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.birdview.ike.framework.R;
import com.birdview.ike.main.bid.BidFragment;
import com.birdview.ike.main.home.HomeFragment;
import com.birdview.ike.main.message.MessageFragment;
import com.birdview.ike.main.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainNavActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
    }


    private boolean loadFragment(Fragment fragment)
    {
        if (fragment != null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment,fragment)
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId())
        {
            case R.id.navigation_home:

                fragment = new HomeFragment();
                break;

            case R.id.navigation_bid:
                fragment = new BidFragment();
                break;

            case R.id.navigation_message:
                fragment = new MessageFragment();
                break;

            case R.id.navigation_profile:
                fragment = new ProfileFragment();


        }
        return loadFragment(fragment);
    }
    }

