package com.birdview.ike.main.home.flight.flightDetail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.birdview.ike.framework.R;

import java.util.ArrayList;
import java.util.List;

public class FightDetailFragment extends Fragment {


    RecyclerView recyclerView;
    FlightDataAdapter flightDataAdapter;
    List<FlightData> flightDataList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.flight_detail_recycler,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        FlightDataAdapter flightDataAdapter = new FlightDataAdapter(getContext(),flightDataList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(flightDataAdapter);
        return view;


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        flightDataList = new ArrayList<>();

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
    }
}
