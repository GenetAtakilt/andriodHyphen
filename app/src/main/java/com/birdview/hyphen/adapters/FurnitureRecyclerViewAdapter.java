package com.birdview.hyphen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.birdview.hyphen.R;
import com.birdview.hyphen.models.FurnitureData;

import java.util.List;

public class FurnitureRecyclerViewAdapter extends RecyclerView.Adapter<FurnitureRecyclerViewAdapter.myViewHolder> {
    public Context scontext;
    public List<FurnitureData> houseDetails;

    public FurnitureRecyclerViewAdapter(Context scontext, List<FurnitureData> houseDetails) {
        this.scontext = scontext;
        this.houseDetails = houseDetails;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.house_item, viewGroup, false);
        return new FurnitureRecyclerViewAdapter.myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        FurnitureData dataFurniture= houseDetails.get(i);
        myViewHolder.fromLoc.setText(dataFurniture.getFromLoc());
        myViewHolder.toLoc.setText(dataFurniture.getToLoc());
        myViewHolder.calendertxt.setText(dataFurniture.getCalaendertxt());
        myViewHolder.size.setText(dataFurniture.getSize());
        myViewHolder.price.setText(dataFurniture.getPrice());
        myViewHolder.calender.setImageResource(dataFurniture.getCalender());
        myViewHolder.icon.setImageResource(dataFurniture.getIcon());
        myViewHolder.toicon.setImageResource(dataFurniture.getToIcon());
        myViewHolder.progress.setProgress(50);
        myViewHolder.progressp.setProgress(50);


    }

    @Override
    public int getItemCount() {
        return houseDetails.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        public TextView fromLoc, toLoc,calendertxt, price,size;
        public ImageView icon,toicon,calender;
        public ProgressBar progress,progressp;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            progress = (ProgressBar) itemView.findViewById(R.id.house_progress);
            progressp = (ProgressBar) itemView.findViewById(R.id.progressp);
            calendertxt = (TextView) itemView.findViewById(R.id.calendertxt);
            fromLoc = (TextView) itemView.findViewById(R.id.fromLoc);
            toLoc = (TextView) itemView.findViewById(R.id.toLoc);
            size = (TextView) itemView.findViewById(R.id.size);
            icon =(ImageView) itemView.findViewById(R.id.icon);
            toicon =(ImageView) itemView.findViewById(R.id.toicon);
            calender =(ImageView) itemView.findViewById(R.id.calender);
            price = (TextView) itemView.findViewById(R.id.price);


        }
    }
}
