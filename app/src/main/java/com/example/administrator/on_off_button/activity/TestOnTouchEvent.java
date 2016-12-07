package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.on_off_button.MyToggleButton;
import com.example.administrator.on_off_button.R;

/**
 * Created by yangchun on 2016-11-29.
 */

public class TestOnTouchEvent extends Activity implements View.OnTouchListener {
    private MyToggleButton mMyToggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ontouch);
        mMyToggleButton = (MyToggleButton) findViewById(R.id.my_toggle_btn);
        mMyToggleButton.setOnTouchListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("yc-onTouchEvent", "onTouchEvent");
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("yc-activity","onTouch");
        return false;
    }
}
