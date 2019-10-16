package com.example.ko_run.hyphendevelopment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class FurnitureRcyclerViewAdapter extends RecyclerView.Adapter<FurnitureRcyclerViewAdapter.myViewHolder> {
    public Context scontext;
    public List<DataFurniture> houseDetails;

    public FurnitureRcyclerViewAdapter(Context scontext, List<DataFurniture> houseDetails) {
        this.scontext = scontext;
        this.houseDetails = houseDetails;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.house_item, viewGroup, false);
        return new FurnitureRcyclerViewAdapter.myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        DataFurniture dataFurniture= houseDetails.get(i);
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
            progress = (ProgressBar) itemView.findViewById(R.id.progress);
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
