package com.example.administrator.on_off_button.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.on_off_button.R;
import com.example.administrator.on_off_button.SeekBarHint;

/**
 * Created by Administrator on 2017/5/29.
 */
public class TestSeekBarActivity extends AppCompatActivity implements SeekBarHint.OnSeekBarHintProgressChangeListener {
    private SeekBarHint mSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_seekbar);
        mSeekBar = (SeekBarHint) findViewById(R.id.seekbar);
        mSeekBar.setPopupStyle(SeekBarHint.POPUP_FOLLOW);
        mSeekBar.setLeftText(1.0f);
        mSeekBar.setRightText(10.0f);

        mSeekBar.setOnProgressChangeListener(this);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        mSeekBar.setProgressText(5);
        mSeekBar.setLeftText(0);
        mSeekBar.setRightText(10);
        mSeekBar.setOnProgressChangeListener(this);
        mSeekBar.post(new Runnable(){
            public void run(){
                mSeekBar.initShow();
                //mSeekBar.setMax();
            }
        });
    }


    @Override
    public String onHintTextChanged(SeekBarHint seekBarHint, float progress) {
        //return "p: "+progress;
        return null;
    }
}
