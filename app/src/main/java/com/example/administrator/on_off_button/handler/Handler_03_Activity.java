package com.example.administrator.on_off_button.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.example.administrator.on_off_button.R;

/**
 * Created by Administrator on 2016/3/6.
 */
public class Handler_03_Activity  extends Activity {
    private HandlerThread handlerThread;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_03);
        handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                Log.i("current Thread",Thread.currentThread()+"");
            }
        };
        handler.sendEmptyMessage(1);
    }
}
