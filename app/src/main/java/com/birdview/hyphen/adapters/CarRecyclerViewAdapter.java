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
import com.birdview.hyphen.models.CarData;

import java.util.List;

public class CarRecyclerViewAdapter extends RecyclerView.Adapter<CarRecyclerViewAdapter.myViewHolder> {

    private Context mcontext;
    private List<CarData> textDetails;

    public CarRecyclerViewAdapter(Context mcontext, List<CarData> textDetails) {
        this.mcontext = mcontext;
        this.textDetails = textDetails;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.car_item, viewGroup, false);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        CarData dataModel = textDetails.get(i);
        myViewHolder.fromLoc.setText(dataModel.getFromLoc());
        myViewHolder.progressBar.setProgress(50);
        myViewHolder.toLoc.setText(dataModel.getToLoc());
        myViewHolder.calendertxt.setText(dataModel.getCalendertxt());
        myViewHolder.size.setText(dataModel.getSize());
        myViewHolder.icon.setImageResource(dataModel.getIcon());
        myViewHolder.toicon.setImageResource(dataModel.getToIcon());
        myViewHolder.calender.setImageResource(dataModel.getCalender());
        myViewHolder.cartype.setText(dataModel.getCartype());
        myViewHolder.carcode.setText(dataModel.getCarcode());
        myViewHolder.txtclr.setText(dataModel.getTxtclr());
        myViewHolder.carname.setText(dataModel.getCarname());
        myViewHolder.imgclr.setImageResource(dataModel.getImgclr());
        myViewHolder.imgcmr.setImageResource(dataModel.getImgcmr());
        myViewHolder.iconclr.setImageResource(dataModel.getIconclr());
        myViewHolder.iconcar.setImageResource(dataModel.getIconcar());
    }

    @Override
    public int getItemCount() {
        return textDetails.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        public TextView fromLoc, toLoc, calendertxt,size,cartype,carcode,txtclr,carname;
        public ImageView icon,toicon,calender,imgclr,imgcmr,iconclr,iconcar;
        public ProgressBar progressBar;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
            fromLoc = (TextView) itemView.findViewById(R.id.fromLoc);
            toLoc = (TextView) itemView.findViewById(R.id.toLoc);
            size = (TextView) itemView.findViewById(R.id.size);
            calendertxt = (TextView) itemView.findViewById(R.id.calendertxt);
            icon =(ImageView) itemView.findViewById(R.id.icon);
            toicon =(ImageView) itemView.findViewById(R.id.toicon);
            calender =(ImageView) itemView.findViewById(R.id.calender);
            cartype = (TextView) itemView.findViewById(R.id.cartype);
            carcode = (TextView) itemView.findViewById(R.id.carcode);
            txtclr = (TextView) itemView.findViewById(R.id.txtclr);
            carname = (TextView) itemView.findViewById(R.id.carname);
            imgclr =(ImageView) itemView.findViewById(R.id.imgclr);
            imgcmr =(ImageView) itemView.findViewById(R.id.imgcmr);
            iconclr =(ImageView) itemView.findViewById(R.id.iconclr);
            iconcar =(ImageView) itemView.findViewById(R.id.iconcar);

        }
    }
}
