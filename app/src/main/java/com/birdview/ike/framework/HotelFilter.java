package com.birdview.ike.framework;

import android.os.Bundle;

import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HotelFilter extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FilterAdapter adapter;
    private List<Filters> data;
    private Switch button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_filter);

        recyclerView = (RecyclerView) findViewById(R.id.filter);

        data = new ArrayList<>();
        adapter = new FilterAdapter(this, data);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        button = (Switch) findViewById(R.id.togle);



        prepareDetails();

    }

    private void prepareDetails() {

        Filters a = new Filters("Bedrooms: +3","",50, button);
        data.add(a);
        Filters c = new Filters("Price: $160","",25, button);
        data.add(c);
        Filters b = new Filters("","Wiﬁ",0,button );
        data.add(b);
        Filters d = new Filters("","Breakfast",0, button);
        data.add(d);
        Filters e = new Filters("","Pool",0, button);
        data.add(e);
        Filters f = new Filters("","Parking",0, button);
        data.add(f);
        Filters g = new Filters("","Pets",0, button);
        data.add(g);
        Filters h = new Filters("","Gym",0, button);
        data.add(h);
        if (Filters.roomprogress == 0){
            setVisible(false);
        }
    }

}
