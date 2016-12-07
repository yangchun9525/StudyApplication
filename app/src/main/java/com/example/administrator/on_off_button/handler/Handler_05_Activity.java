package com.example.administrator.on_off_button.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.administrator.on_off_button.R;


/**
 * Created by Administrator on 2016/3/6.
 */
public class Handler_05_Activity  extends Activity {
    private TextView tv;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            tv.setText("OK");
        }
    };
    private void handle1(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv.setText("OK");
            }
        });
    }

    private void handle2(){
        handler.sendEmptyMessage(1);
    }

    private void updateUI(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText("OK");
            }
        });
    }

    private void viewUI(){
        tv.post(new Runnable() {
            @Override
            public void run() {
                tv.setText("OK");
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_05);
        tv = (TextView) findViewById(R.id.tv);
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(2000);
                    updateUI();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
