package com.example.administrator.on_off_button.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.on_off_button.R;

/**
 * Created by Administrator on 2016/3/6.
 */
public class Handler_01_Activity extends Activity{
    private TextView tv;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //此方法是对下面的handleMessage的一个拦截，false表示不拦截，true就不会执行下面的
            Toast.makeText(Handler_01_Activity.this,"1",Toast.LENGTH_SHORT).show();
            return false;
        }
    }){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(Handler_01_Activity.this,"2",Toast.LENGTH_SHORT).show();
            tv.setText(msg.arg1+"");
        }
    };
    private ImageView iv;
    private int images[] = {R.drawable.test,R.drawable.test2,R.drawable.test3};
    private int index = 0;
    private MyRunnable myRunnable = new MyRunnable();
    class MyRunnable implements Runnable{

        @Override
        public void run() {
            index ++;
            index = index % 3;
            iv.setImageResource(images[index]);
            handler.postDelayed(this, 1000);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_01);
        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);
        handler.postDelayed(myRunnable,1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message msg = handler.obtainMessage();
                    msg.arg1 = 1;
//                    handler.sendMessage(msg);
                    msg.sendToTarget();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                tv.setText("update Thread");
//            }
//        },1000);

    }
    public void toRemoveImageMessage(View view){
        handler.removeCallbacks(myRunnable);
    }

}
