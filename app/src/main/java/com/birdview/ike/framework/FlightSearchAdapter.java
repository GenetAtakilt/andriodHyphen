package com.birdview.ike.framework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlightSearchAdapter extends RecyclerView.Adapter<FlightSearchAdapter.FlightSearchViewHolder> {

    private Context mCtx;
    private List<FlightSearch> flightSearches;

    public FlightSearchAdapter(Context mCtx, List<FlightSearch> flightSearches) {
        this.mCtx = mCtx;
        this.flightSearches = flightSearches;
    }

    @NonNull
    @Override
    public FlightSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.flight_search_card, null);
        return new FlightSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightSearchViewHolder holder, int position) {

        FlightSearch flightSearch = flightSearches.get(position);

        holder.data.setText(flightSearch.getData());
        holder.data1.setText(flightSearch.getData1());
        holder.image_icon.setImageResource(flightSearch.getIcon());
        holder.image_icon1.setImageResource(flightSearch.getIcon1());





    }

    @Override
    public int getItemCount() {
        return flightSearches.size();
    }


    class FlightSearchViewHolder extends RecyclerView.ViewHolder
    {
        TextView data,data1;
        ImageView image_icon,image_icon1;

        public FlightSearchViewHolder(@NonNull View itemView) {
            super(itemView);

            data = itemView.findViewById(R.id.data);
            data1 = itemView.findViewById(R.id.data1);
            image_icon = itemView.findViewById(R.id.icon_image);
            image_icon1 =itemView.findViewById(R.id.icon_image1);



        }
    }
}
