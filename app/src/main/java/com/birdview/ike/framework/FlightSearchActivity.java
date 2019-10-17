package com.birdview.ike.framework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.birdview.ike.framework.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class FlightSearchActivity extends BaseActivity {

     Button flightSearch;

    List<FlightSearch> flightSearches;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_search_recycler);

        flightSearch = findViewById(R.id.button);
        flightSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FlightSearchActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });



        recyclerView = findViewById(R.id.flightlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        flightSearches = new ArrayList<>();

       flightSearches.add(new FlightSearch(1,"Los Angeles","Las Vegas",R.drawable.ic_place_black_24dp,R.drawable.ic_place_black_24dp));
       flightSearches.add(new FlightSearch(1,"Add a return Flight","October 1,2019,Tuesday",R.drawable.ic_unfold_more_black_24dp,R.drawable.ic_date_range_black_24dp));


        FlightSearchAdapter adapter = new FlightSearchAdapter(this, flightSearches);
        recyclerView.setAdapter(adapter);
    }
}
