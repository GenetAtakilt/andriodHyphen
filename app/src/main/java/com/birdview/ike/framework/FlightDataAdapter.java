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

public class FlightDataAdapter extends RecyclerView.Adapter<FlightDataAdapter.FlightDataViewHolder> {

    private Context mCtx;
    private List<FlightData> flightDataList;

    public FlightDataAdapter(Context mCtx, List<FlightData> flightDataList) {
        this.mCtx = mCtx;
        this.flightDataList = flightDataList;
    }

    @NonNull
    @Override
    public FlightDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_card_view,null);
        return new FlightDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightDataViewHolder holder, int position) {
       FlightData flightData = flightDataList.get(position);
       holder.price.setText((String.valueOf (flightData.getPrice())));
       holder.place.setText(flightData.getPlace());
       holder.duration.setText(flightData.getDuration());
       holder.stops.setText(flightData.getStops());
       holder.range.setText(flightData.getRange());
       holder.time.setText(flightData.getTime());
       holder.type.setText(flightData.getType());
       holder.image.setImageResource(flightData.getImage());



    }

    @Override
    public int getItemCount() {
        return flightDataList.size();
    }

    class FlightDataViewHolder extends RecyclerView.ViewHolder
    {
        TextView price, place,duration,stops,range,time,type;
        ImageView image;


        public FlightDataViewHolder(@NonNull View itemView) {
            super(itemView);


            price = itemView.findViewById(R.id.textViewPrice);
           place = itemView.findViewById(R.id.textViewPlace);
            duration = itemView.findViewById(R.id.textViewDuration);
            stops = itemView.findViewById(R.id.textViewStops);
            range = itemView.findViewById(R.id.textViewRange);
            time = itemView.findViewById(R.id.textViewTime);
            type = itemView.findViewById(R.id.textViewType);
            image = itemView.findViewById(R.id.flightimage);

        }
    }
}
