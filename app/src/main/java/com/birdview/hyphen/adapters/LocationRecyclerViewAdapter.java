package com.birdview.hyphen.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.birdview.hyphen.R;
import com.birdview.hyphen.models.SingleRecyclerViewLocation;

import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.util.List;

public class LocationRecyclerViewAdapter extends
        RecyclerView.Adapter<LocationRecyclerViewAdapter.MyViewHolder> {

    private List<SingleRecyclerViewLocation> locationList;
    private MapboxMap mapboxMap;

    public LocationRecyclerViewAdapter(List<SingleRecyclerViewLocation> locationList, MapboxMap mapBoxMap) {
        this.locationList = locationList;
        this.mapboxMap = mapBoxMap;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_on_top_map_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SingleRecyclerViewLocation singleRecyclerViewLocation = locationList.get(position);
        holder.name.setText(singleRecyclerViewLocation.getName());
        holder.numOfBeds.setText(singleRecyclerViewLocation.getBedInfo());


        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                LatLng selectedLocationLatLng = locationList.get(position).getLocationCoordinates();
                CameraPosition newCameraPosition = new CameraPosition.Builder()
                        .target(selectedLocationLatLng)
                        .build();
                 mapboxMap.easeCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition));

                }
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView numOfBeds;
        CardView singleCard;
        ItemClickListener clickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.location_title_tv);
            numOfBeds = itemView.findViewById(R.id.location_num_of_beds_tv);
            singleCard = itemView.findViewById(R.id.single_location_cardview);
            singleCard.setOnClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view ) {
            clickListener.onClick(view, getLayoutPosition());
        }
    }
    public  interface ItemClickListener {

        void onClick(View view, int position);
    }
}

