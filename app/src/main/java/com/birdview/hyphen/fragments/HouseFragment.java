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
import com.birdview.hyphen.adapters.FurnitureRecyclerViewAdapter;
import com.birdview.hyphen.models.FurnitureData;

import java.util.ArrayList;
import java.util.List;

//import android.support.v7.app.AlertController;

public class HouseFragment extends Fragment {
    View v;
    private RecyclerView myRecyclerView;
    private List<FurnitureData> lstData;


    public HouseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.house_fragment, container, false);
        myRecyclerView =(RecyclerView) v.findViewById(R.id.house_recycler);
        FurnitureRecyclerViewAdapter furnitureRcyclerViewAdapter = new FurnitureRecyclerViewAdapter(getContext(), lstData);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(furnitureRcyclerViewAdapter);
        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstData = new ArrayList<>();
        lstData.add(new FurnitureData("Las Vegas", "Los Angeles", "October 1, 2019, Tuesday", "Number of rooms: up to 2",
                "Price: up to $346", R.drawable.ic_date_range_black_24dp, R.drawable.ic_location_on_black_24dp, R.drawable.ic_location_on_black_24dp, 50, 50));
    }

//    public void showOtherFragment()
//    {
//        Fragment fr=new CarFragment();
//        FragmentChangerListner fc=(FragmentChangerListener)getActivity();
//        fc.replaceFragment(fr);
//    }
}
