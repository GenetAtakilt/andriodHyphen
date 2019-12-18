package com.birdview.hyphen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.birdview.hyphen.R;

public class FlightsTabFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "Flights";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_flight, container, false);
    }

    @Override
    public void onClick(View v) {

    }
}
