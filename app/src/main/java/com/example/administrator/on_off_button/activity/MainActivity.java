package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.on_off_button.AudioService;
import com.example.administrator.on_off_button.MyToggleButton;
import com.example.administrator.on_off_button.PPTCircleView;
import com.example.administrator.on_off_button.R;
import com.example.administrator.on_off_button.TopBar;

import java.io.IOException;
public class MainActivity extends Activity implements TopBar.TopBaiClickInterface {
    private MediaPlayer mMediaPlayer;
    private TopBar topBar;
    private PPTCircleView pptCircleView;
    private AudioService audioService;
    private Button button;
    private MyToggleButton my_toggle_btn;
    Intent intent = new Intent();
    //使用ServiceConnection来监听Service状态的变化
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            audioService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            //这里我们实例化audioService,通过binder来实现
            audioService = ((AudioService.AudioBinder)binder).getService();

        }
    };
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_toggle_btn = (MyToggleButton) findViewById(R.id.my_toggle_btn);
//        my_toggle_btn.setOnTouchListener(this);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MatrixActivity.class));
            }
        });
//        playLocalFile();
        topBar = (TopBar) findViewById(R.id.topBar);
        topBar.setOnTopBarClickListener(this);
//        topBar.setButtonVisable(0, false);

        pptCircleView = (PPTCircleView) findViewById(R.id.PPTCircleView);


        intent.setClass(this, AudioService.class);
        startService(intent);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void leftClick() {
        startActivity(new Intent(MainActivity.this, ViewGroupActivity.class));
        Toast.makeText(this, "click left", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void rightClick() {
        startActivity(new Intent(MainActivity.this,Mp3Activity.class));
        audioService.player.reset();
        audioService.stopSelf();
//        audioService.haveFun();
        Toast.makeText(this, "click right", Toast.LENGTH_SHORT).show();
    }

    private void playLocalFile() {
        mMediaPlayer = MediaPlayer.create(this, R.raw.yicijiuhao);
        //播放工程res目录下的raw目录中的音乐文件in_call_alarm

        try {
            mMediaPlayer.prepare();
        } catch (IllegalStateException e) {

        } catch (IOException e) {

        }

        mMediaPlayer.start();
//        headsetplay.setEnabled(false);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playLocalFile();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
        stopService(intent);
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer = null;
        }
    }
    public void toBitmapShader(View view){
        startActivity(new Intent(MainActivity.this, BitmapShaderActivity.class));
    }

    public void toMoreActivity(View view){
        startActivity(new Intent(MainActivity.this,MoreActivity.class));
    }
    public void toFlipBook(View view){
        startActivity(new Intent(MainActivity.this,FlipBookActivity.class));
    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        Log.i("yc-onTouch", "onTouch");
//        return false;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("yc-1111111111111", "onTouchEvent");
        return super.onTouchEvent(event);
    }
}
