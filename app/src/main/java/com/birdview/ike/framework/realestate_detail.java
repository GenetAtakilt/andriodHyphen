package com.birdview.ike.framework;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class realestate_detail extends Activity {
    private RecyclerView recyclerViews;
    private Adapter adapter;
    private List<DataModel> dataModels;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.realestate_detail);

        recyclerViews = (RecyclerView) findViewById(R.id.recyclerViews);


        dataModels = new ArrayList<>();
        adapter = new Adapter(this, dataModels);

        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(this);
        recyclerViews.setLayoutManager(rLayoutManager);
        recyclerViews.setAdapter(adapter);

        prepareDetails();

        try {
            Glide.with(realestate_detail.this).load(R.drawable.hotels).into((ImageView) findViewById(R.id.imageView));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    private void prepareDetails() {

        DataModel a = new DataModel("$8,600/month", "6 bd. | 3 ba | 2,386 sqft");
        dataModels.add(a);

        a = new DataModel("1104 Greentree Ct,", "Lexington, KY");
        dataModels.add(a);

        a = new DataModel("Amenities", " Year built:        2010 \n Type:                House \n Parking:           Private parking for 3 cars");
        dataModels.add(a);

        a = new DataModel("Description", "This townhouse is neat as a pin. It has been well maintained, vacant and waiting for a new resident to take over.");
        dataModels.add(a);



        adapter.notifyDataSetChanged();
    }

}
