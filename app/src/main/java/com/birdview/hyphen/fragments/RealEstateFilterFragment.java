package com.birdview.hyphen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.birdview.hyphen.R;

public class RealEstateFilterFragment extends Fragment {
    private TextView bedroomNo;
    private SeekBar bedroomSeekBar;
    private final int MAX_BEDROOM = 3;
    private TextView bathroomNo;
    private SeekBar bathroomSeekBar;
    private final int MAX_BATHROOM = 4;
    private TextView parkingNo;
    private SeekBar parkingSeekBar;
    private final int MAX_PARKING = 2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_frag_re, container, false);
        bedroomNo = view.findViewById(R.id.filter_re_textview_bedroomNo);
        bedroomSeekBar = view.findViewById(R.id.filter_re_progressBar_bedroom);
        bathroomNo = view.findViewById(R.id.filter_re_textview_bathroomNo);
        bathroomSeekBar = view.findViewById(R.id.filter_re_progressBar_bathroom);
        parkingNo = view.findViewById(R.id.filter_re_textview_parkingNo);
        parkingSeekBar = view.findViewById(R.id.filter_re_progressBar_parking);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bedroomSeekBar.setMax(MAX_BEDROOM);
        bedroomSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress<MAX_BEDROOM){
                    bedroomNo.setText(""+progress);
                } else {
                    bedroomNo.setText("3+");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        bathroomSeekBar.setMax(MAX_BATHROOM);
        bathroomSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress<MAX_BATHROOM){
                    bathroomNo.setText(""+progress);
                } else {
                    bathroomNo.setText("4+");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        parkingSeekBar.setMax(MAX_PARKING);
        parkingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress<MAX_PARKING){
                    parkingNo.setText(""+progress);
                } else {
                    parkingNo.setText("2+");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
