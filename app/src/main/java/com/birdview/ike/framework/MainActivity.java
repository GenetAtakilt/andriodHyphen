package com.example.ko_run.hyphendevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button hotel;
    private Button moving;
    private Button realestate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        hotel = (Button) findViewById(R.id.hotel);
        moving = (Button) findViewById(R.id.moving);
        realestate = (Button) findViewById(R.id.realestate);
        imageView = (ImageView) findViewById(R.id.imageView);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void toHotel(View view) {
        Intent intent = new Intent(this, hotel_detail.class);
        startActivity(intent);

    }

    public void toRealestate(View view) {
        Intent intent = new Intent(this, realestate_detail.class);
        startActivity(intent);


    }

    public void toMoving(View view) {
        Intent intent = new Intent(this, Moving.class);
        startActivity(intent);
    }

    public void toHotelFilter(View view) {
        Intent intent = new Intent(this, HotelFilter.class);
        startActivity(intent);
    }
}
