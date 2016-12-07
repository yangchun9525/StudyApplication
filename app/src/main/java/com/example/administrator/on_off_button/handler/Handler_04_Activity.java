package com.example.administrator.on_off_button.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.administrator.on_off_button.R;

/**
 * Created by Administrator on 2016/3/6.
 */
public class Handler_04_Activity extends Activity {
    //创建主线程的handler
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Message msg1 = new Message();
            Log.i("test","main handler");
            //向子线程发送消息
            threadHandler.sendMessageDelayed(msg1, 1000);
        }
    };
    private Handler threadHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_04);
        HandlerThread thread = new HandlerThread("handlerThread");
        thread.start();
        //创建子线程的handler
        threadHandler = new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                Message msg1 = new Message();
                Log.i("test", "son handler");
                //向主线程发送消息
                handler.sendMessageDelayed(msg1, 1000);
            }
        };
    }
    public void send(View view){
        handler.sendEmptyMessage(1);
    }
    public void stop(View view){
        handler.removeMessages(1);
    }
}
