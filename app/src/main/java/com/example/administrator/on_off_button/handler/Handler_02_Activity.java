package com.example.administrator.on_off_button.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.on_off_button.R;

/**
 * Created by Administrator on 2016/3/6.
 */
public class Handler_02_Activity extends Activity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i("currentThread--UI",Thread.currentThread()+"");
        }
    };

    class MyThread extends Thread{
        public Handler hand;
        public Looper looper;
        @Override
        public void run() {
            //获取looper
            Looper.prepare();
            looper = Looper.myLooper();
            hand = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Log.i("currentThread",Thread.currentThread()+"");
                }
            };
            //将当前的子线程与Looper进行关联
            Looper.loop();
        }
    }
    private MyThread thread;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_02);
        tv = (TextView) findViewById(R.id.tv);
        thread = new MyThread();
        thread.start();
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread.hand.sendEmptyMessage(1);
//        handler.sendEmptyMessage(1);
//        handler = new Handler(thread.looper){
//            @Override
//            public void handleMessage(Message msg) {
//                Log.i("currentThread",msg+"");
//            }
//        };
//        handler.sendEmptyMessage(1);
    }
}
