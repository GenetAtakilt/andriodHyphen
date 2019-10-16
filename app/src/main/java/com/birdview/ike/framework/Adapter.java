package com.example.ko_run.hyphendevelopment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context mcontext;
    private List<DataModel> textDetails;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        DataModel dataModel = textDetails.get(position);
        myViewHolder.title.setText(dataModel.getTitle());
        myViewHolder.description.setText(dataModel.getDescription());

    }

    @Override
    public int getItemCount() {
        return textDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
        }

    }
    public Adapter(Context mcontext, List<DataModel> textDetail){
        this.mcontext = mcontext;
        this.textDetails = textDetail;

    }
}
