package com.birdview.ike.framework;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Moving extends AppCompatActivity {
    private Button house_item;
    private Button car;
    private MViewPagerAdapter adapter;


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
        adapter = new MViewPagerAdapter(getSupportFragmentManager());
//        viewPager = (ViewPager) findViewById(R.id.veiwpager_id);



 //       adapter.addsFragment(new HouseFragment(),"House Items");
   //     adapter.addsFragment(new CarFragment(), "Cars");
        HouseFragment houseFragment = new HouseFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
       // fragmentTransaction.replace(R.id.veiwpager_id, houseFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

//        viewPager.setAdapter(adapter);


        house_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HouseFragment houseFragment = new HouseFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
             //   fragmentTransaction.replace(R.id.veiwpager_id, houseFragment);
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
             //   fragmentTransaction.replace(R.id.veiwpager_id, carFragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}
