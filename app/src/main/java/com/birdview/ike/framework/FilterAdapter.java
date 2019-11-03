package com.birdview.ike.framework;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.MyViewHolder> {

    private Context fcontext;
    private List<Filters> datafilters;

    public FilterAdapter(Context fcontext, List<Filters> datafilters) {
        this.fcontext =fcontext;
        this.datafilters = datafilters;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView =LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.card_filter, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Filters newFilter = datafilters.get(i);
        MyViewHolder.size.setText(newFilter.getSize());
        MyViewHolder.filters.setText(newFilter.getFilters());
        myViewHolder.roomprogress.setProgress(newFilter.getRoomprogress());

    }

    @Override
    public int getItemCount() {
        return datafilters.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public static TextView size;
        public static TextView filters;
        public ProgressBar roomprogress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            size = (TextView) itemView.findViewById(R.id.size);
            filters = (TextView) itemView.findViewById(R.id.filters);
            roomprogress = (ProgressBar) itemView.findViewById(R.id.roomprogress);
        }

    }
}
