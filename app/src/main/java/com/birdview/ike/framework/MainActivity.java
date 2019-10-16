package com.birdview.ike.framework;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.birdview.ike.framework.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    RecyclerView recyclerView;
    FlightDataAdapter flightDataAdapter;
    List<FlightData> flightDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        flightDataList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        flightDataList.add(new FlightData(1,"$46","LAS - LAX","Duration","Stops","6:10a - 7:28a","01h 21m","Direct",R.drawable.ic_flight_takeoff_black_24dp));
        FlightDataAdapter adapter = new FlightDataAdapter(this,flightDataList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
