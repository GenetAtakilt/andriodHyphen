package com.example.ko_run.hyphendevelopment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class hotel_detail extends Activity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<DataModel> dataModels;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        dataModels = new ArrayList<>();
        adapter = new Adapter(this, dataModels);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);


        
        prepareDetails();

        try {
            Glide.with(hotel_detail.this).load(R.drawable.hotels).into((ImageView) findViewById(R.id.hotel));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    private void prepareDetails() {
        String img= "drawable://"+ R.drawable.ic_wifi_black_24dp;
        String img1= "drawable://"+ R.drawable.ic_tv_black_24dp;
        String img2= "drawable://"+ R.drawable.ic_drive_eta_black_24dp;
        String img3= "drawable://"+ R.drawable.ic_restaurant_black_24dp;
        String img4= "drawable://"+ R.drawable.ic_local_bar_black_24dp;

            DataModel a = new DataModel("The Michelangelo", "91% | Times Square");
            dataModels.add(a);

            a = new DataModel("152 W 51st Street \n New York, NY ", "Times Square");
            dataModels.add(a);

            a = new DataModel("Amenities", " Complimentary wiﬁ \n 42” ﬂat-screen \n Self-service parking $45/night \n Michelangelo Lounge serves American cuisine \n Drinks served at Michelangelo Lounge");
            dataModels.add(a);



        adapter.notifyDataSetChanged();
    }

}
