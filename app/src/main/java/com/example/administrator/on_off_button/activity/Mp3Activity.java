package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.administrator.on_off_button.AudioService;
import com.example.administrator.on_off_button.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/7.
 */
public class Mp3Activity extends Activity {
    class MusicFilter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String filename) {

            return (filename.endsWith(".mp3") && filename.endsWith(".ape"));
        }
    }

    private TextView currentMusicName;
    private boolean isStart = true;
    private boolean isPause = true;
    private TextView mNextBtn = null;
    private TextView mLastBtn = null;
    private TextView mSartPauseBtn = null;
    private TextView mStopBtn = null;
    private MediaPlayer mMediaPlayer = null;
    private List<String> mMusicList = new ArrayList<String>();
    private int currentId = 0;
    //    private static final String MUSIC_PATH = new String("/sdcard/2345/");
    private static final String MUSIC_PATH = new String(Environment.getExternalStorageDirectory().getAbsolutePath());
    private AudioService audioService;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3);
//        updateMusicList();
        mMusicList = getList();
        for (int i=0;i<mMusicList.size();i++){
            Log.i("test music path:",mMusicList.get(i));
        }
        intent.setClass(this, AudioService.class);

        mMediaPlayer = new MediaPlayer();
        mNextBtn = (TextView) findViewById(R.id.nextButton);
        mLastBtn = (TextView) findViewById(R.id.lastButton);
        mSartPauseBtn = (TextView) findViewById(R.id.startPauseButton);
        mStopBtn = (TextView) findViewById(R.id.stopButton);
        currentMusicName = (TextView) findViewById(R.id.currentMusicName);

        mNextBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                nextMusic();
            }
        });

        mLastBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                lastMusic();
            }
        });

        mSartPauseBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(isStart){
                    playMusic(mMusicList.get(currentId));
//					playMusic(MUSIC_PATH + mMusicList.get(currentId));
                }else {
                    if(!isPause){
                        mMediaPlayer.pause();
                    }else {
                        mMediaPlayer.start();

                    }
                    isPause = !isPause;
                }
                isStart = false;
            }
        });

        mStopBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(mMediaPlayer.isPlaying()){
                    mMediaPlayer.reset();
                }
                isStart = true;
            }
        });
    }


    protected void playMusic(String path) {
        try {
            currentMusicName.setText(path);
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer arg0) {
                    nextMusic();

                }
            });
        } catch (Exception e) {
        }
    }

    //用于播放前一手歌
    protected void lastMusic() {
        if (currentId == 0) {
            currentId = mMusicList.size() - 1;
        }else {
            currentId --;
        }
        playMusic(mMusicList.get(currentId));
    }

    //用于播放后一手歌
    protected void nextMusic() {
        if(currentId == (mMusicList.size() - 1)){
            currentId = 0;
        }else {
            currentId++;
        }
        playMusic(mMusicList.get(currentId));
//		playMusic(MUSIC_PATH + mMusicList.get(currentId));
    }


    private void updateMusicList() {
        File path = new File(MUSIC_PATH);
        if(path.listFiles(new MusicFilter()).length > 0){
            for(File file:path.listFiles(new MusicFilter())){
                mMusicList.add(file.getName());
                Log.i("path", file.getName());
            }
        }
    }


    public boolean onKeyDown(int ketCode,KeyEvent event){
        if(ketCode == KeyEvent.KEYCODE_BACK){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            this.finish();
            return true;
        }
        return super.onKeyDown(ketCode, event);

    }

    private List<String> getList(){
        List<String> musicList = new ArrayList<String>();
        ContentResolver musicResolver = this.getContentResolver();
        Cursor musicCursor = musicResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                null);

        int musicColumnIndex;

        if (null != musicCursor && musicCursor.getCount() > 0) {
            for (musicCursor.moveToFirst(); !musicCursor.isAfterLast(); musicCursor.moveToNext()) {

                String musicPath = "";
                // 取得音乐播放路径
                musicColumnIndex = musicCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
                musicPath = musicCursor.getString(musicColumnIndex);
                Log.i("musicPath", musicPath);

                //		// 取得音乐的名字
                //		musicColumnIndex = musicCursor
                //		.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
                //		musicName = musicCursor.getString(musicColumnIndex);
                //		musicDataMap.put("musicName", musicName);



                musicList.add(musicPath);
            }
        }
        return musicList;
    }
}

