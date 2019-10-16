package com.example.ko_run.hyphendevelopment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Moving extends AppCompatActivity {
    private Button house_item;
    private Button car;
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moving);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        house_item = (Button) findViewById(R.id.house_item);
        car = (Button) findViewById(R.id.car);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        viewPager = (ViewPager) findViewById(R.id.veiwpager_id);



        adapter.addFragment(new HouseFragment(),"House Items");
        adapter.addFragment(new CarFragment(), "Cars");
        HouseFragment houseFragment = new HouseFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.veiwpager_id, houseFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

//        viewPager.setAdapter(adapter);


        house_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HouseFragment houseFragment = new HouseFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.veiwpager_id, houseFragment);
//        fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarFragment carFragment = new CarFragment();
//                viewPager.setAdapter(adapter);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.veiwpager_id, carFragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}
