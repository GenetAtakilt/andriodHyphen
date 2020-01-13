package com.birdview.hyphen.fragments;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.birdview.hyphen.R;


public class HotelFilterFragment extends Fragment {

    private TextView bedroomNo;
    private SeekBar bedroomSeekBar;
    private static final int MAX_BEDROOM = 3;
    private TextView priceAmt;
    private SeekBar priceSeekBar;
    private static final int MAX_PRICE = 2500;
    private static final int MIN_PRICE = 100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_frag_hotel, container, false);
        bedroomNo = view.findViewById(R.id.filter_hotel_textview_bedroomNo);
        bedroomSeekBar = view.findViewById(R.id.filter_hotel_seekBar_bedroom);
        priceAmt = view.findViewById(R.id.filter_hotel_textview_price_amount);
        priceSeekBar = view.findViewById(R.id.filter_hotel_seekBar_price);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        priceSeekBar.setMin(MIN_PRICE);
        priceSeekBar.setMax(MAX_PRICE);
        priceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress<MAX_PRICE){
                    priceAmt.setText(""+progress);
                } else {
                    priceAmt.setText("2500+");
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
