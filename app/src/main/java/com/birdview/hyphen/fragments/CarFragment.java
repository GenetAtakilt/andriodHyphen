package com.birdview.hyphen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.birdview.hyphen.R;
import com.birdview.hyphen.adapters.CarRecyclerViewAdapter;
import com.birdview.hyphen.models.CarData;

import java.util.ArrayList;
import java.util.List;

public class CarFragment extends Fragment {
    View v;
    private RecyclerView myRecyclerView;
    private List<CarData> lstData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.car_fragment, container, false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.cars_recycler);
        CarRecyclerViewAdapter recyclerViewAdapter = new CarRecyclerViewAdapter(getContext(), lstData);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        lstData = new ArrayList<>();
        lstData.add(new CarData(R.drawable.ic_location_on_black_24dp, R.drawable.ic_location_on_black_24dp, R.drawable.ic_fiber_manual_record_black_24dp, R.drawable.ic_camera_alt_black_24dp,
                R.drawable.ic_brush_black_24dp, R.drawable.ic_drive_eta_black_24dp, R.drawable.ic_date_range_black_24dp, "Las Vegas", "Los Angeles", "" +
                "October 1, 2019, Tuesday", "Price: up to $546", "VIN", "4123 3456 5789", "Red", "Mercedez Benz", 50));

    }
}