package com.example.administrator.on_off_button.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import com.example.administrator.on_off_button.R;

import static android.app.Notification.VISIBILITY_PUBLIC;

/**
 * Created by yangchun on 2016-12-8.
 */

public class NotifyActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
    }

    /**
     * builder.setVisibity
     * Visibity_private --没有锁屏的时候会显示
     * VISIBILITY_PUBLIC --任何时候会显示
     * Visibity_Secret --表明再pin，password等安全锁和没有锁屏的时候显示
     */
    public void normal(View view){
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, 0);
        builder.setSmallIcon(R.drawable.ic_launcher)
                .setVisibility(VISIBILITY_PUBLIC)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
                .setContentTitle("no1")
                .setContentText("content Text")
                .setSubText("sub text");
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }
    public void custom(View view){
        Notification.Builder builder = new Notification.Builder(this);

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notify);

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, 0);
        builder.setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(false);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = builder.build();
        notification.contentView = contentView;

        RemoteViews expandView = new RemoteViews(getPackageName(), R.layout.notify_expand);
        notification.bigContentView = expandView;

        manager.notify(2, notification);
    }
    public void FullScreen(View view){
        Notification.Builder builder = new Notification.Builder(this);
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setSmallIcon(R.drawable.ic_launcher)
                .setFullScreenIntent(pendingIntent, true)
                .setAutoCancel(false)
                //设置notification的优先级，优先级越高的，通知排的越靠前，优先级低的，不会在手机最顶部的状态栏显示图标
                .setPriority(Notification.PRIORITY_HIGH)
                //用来确定显示的位置
                .setCategory(Notification.CATEGORY_CALL)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
                .setContentTitle("no1")
                .setContentText("content Text")
                .setSubText("sub text");
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(3, builder.build());
    }
}
