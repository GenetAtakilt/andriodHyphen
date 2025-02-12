package com.birdview.ike.framework;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CarFragment extends Fragment {
    View v;
    private RecyclerView myRecyclerView;
    private List<Data> lstData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.car_fragment, container, false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.cars_recycler);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lstData);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        lstData = new ArrayList<>();
        lstData.add(new Data(R.drawable.ic_location_on_black_24dp, R.drawable.ic_location_on_black_24dp, R.drawable.ic_fiber_manual_record_black_24dp, R.drawable.ic_camera_alt_black_24dp,
                R.drawable.ic_brush_black_24dp, R.drawable.ic_drive_eta_black_24dp, R.drawable.ic_date_range_black_24dp, "Las Vegas", "Los Angeles", "" +
                "October 1, 2019, Tuesday", "Price: up to $546", "VIN", "4123 3456 5789", "Red", "Mercedez Benz", 50));

    }
}