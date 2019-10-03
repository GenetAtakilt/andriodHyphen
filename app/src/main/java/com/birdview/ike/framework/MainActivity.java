package com.birdview.ike.framework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.birdview.ike.framework.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
