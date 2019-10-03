package com.birdview.ike.framework.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.birdview.ike.framework.util.Util;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        d("< -------------------- onCreate(Bundle) -------------------- >");

    }

    @Override
    protected void onStart() {
        super.onStart();
        d("< -------------------- onStart -------------------- >");
    }

    @Override
    protected void onResume() {
        super.onResume();
        d("< -------------------- onResume -------------------- >");
    }

    @Override
    protected void onPause() {
        super.onPause();
        d("< -------------------- onPause -------------------- >");
    }

    @Override
    protected void onStop() {
        super.onStop();
        d("< -------------------- onStop -------------------- >");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        d("< -------------------- onDestroy -------------------- >");
    }


    protected void toast(int stringResId) {
        final String message = getText(stringResId).toString();
        toast(message);
    }

    protected void toast(int stringResId, int duration) {
        final String message = getText(stringResId).toString();
        toast(message, duration);
    }

    protected void toast(String message) {
        toast(message, Toast.LENGTH_SHORT);
    }

    protected void toast(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }

    protected void d(String message) {
        Util.d(this, message);
    }
    protected void e(String message) {
        Util.e(this, message);
    }


}
