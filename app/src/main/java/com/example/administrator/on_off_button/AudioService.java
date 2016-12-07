package com.example.administrator.on_off_button;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Administrator on 2016/1/6.
 */
public class AudioService extends Service implements MediaPlayer.OnCompletionListener{

    public MediaPlayer player;

    private final IBinder binder = new AudioBinder();
    @Override
    public IBinder onBind(Intent arg0) {
        return binder;
    }
    /**
     * ��Audio�������ʱ�򴥷��ö���
     */
    @Override
    public void onCompletion(MediaPlayer player) {
        stopSelf();//�����ˣ������Service
    }

    //������������Ҫʵ����MediaPlayer����
    public void onCreate(){
        super.onCreate();
        player = new MediaPlayer();
        try {
            player.setDataSource("/storage/sdcard0/2345/4.mp3");
            player.prepare();
            player.setOnCompletionListener(this);
        } catch (IOException e) {
            Log.i("exception",e+"");
            e.printStackTrace();
        }

        //���Ǵ�raw�ļ����л�ȡһ��Ӧ���Դ���mp3�ļ�
//        player = MediaPlayer.create(this, R.raw.yicijiuhao);
//        player.setOnCompletionListener(this);
    }

    /**
     * �÷�����SDK2.0�ſ�ʼ�еģ����ԭ����onStart����
     */
    public int onStartCommand(Intent intent, int flags, int startId){
        if(!player.isPlaying()){
            player.start();
        }
        return START_STICKY;
    }

    public void onDestroy(){
        //super.onDestroy();
        if(player.isPlaying()){
            player.stop();
        }
        player.release();
    }

    //Ϊ�˺�Activity������������Ҫ����һ��Binder����
    public class AudioBinder extends Binder{

        //����Service����
        public AudioService getService(){
            return AudioService.this;
        }
    }

    //���˲��Ž���
    public void haveFun(){
//        if(player.isPlaying() && player.getCurrentPosition()>2500){
//            player.seekTo(player.getCurrentPosition()-2500);
//        }
        if(player.isPlaying()){
            player.stop();
        }
        player.release();
        player = MediaPlayer.create(this, R.raw.yicijiuhao);
        player.start();
//        try {
//            player.setDataSource(path);
//            player.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}