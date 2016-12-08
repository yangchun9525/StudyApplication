package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.administrator.on_off_button.R;
import com.example.administrator.on_off_button.Util.ImageHelper;

/**
 * Created by Administrator on 2016/2/27.
 */
public class ImageEffectActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private ImageView mImageView;
    private SeekBar mSeekbarhue,mSeekbarSaturation, mSeekbarLum;
    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;
    //色调，饱和度，亮度
    private float mHue,mStauration, mLum;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_effect);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        mImageView = (ImageView) findViewById(R.id.iv);
        mSeekbarhue = (SeekBar) findViewById(R.id.seekBar1);
        mSeekbarSaturation = (SeekBar) findViewById(R.id.seekBar2);
        mSeekbarLum = (SeekBar) findViewById(R.id.seekBar3);
        mSeekbarhue.setOnSeekBarChangeListener(this);
        mSeekbarSaturation.setOnSeekBarChangeListener(this);
        mSeekbarLum.setOnSeekBarChangeListener(this);
        mSeekbarhue.setMax(MAX_VALUE);
        mSeekbarSaturation.setMax(MAX_VALUE);
        mSeekbarLum.setMax(MAX_VALUE);
        mSeekbarhue.setProgress(MID_VALUE);
        mSeekbarSaturation.setProgress(MID_VALUE);
        mSeekbarLum.setProgress(MID_VALUE);
        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekBar1:
                mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.seekBar2:
                mStauration = progress * 1.0F / MID_VALUE;
                break;
            case R.id.seekBar3:
                mLum = progress * 1.0F / MID_VALUE;
                break;
        }
        mImageView.setImageBitmap(ImageHelper.handleImageEffect(bitmap, mHue, mStauration, mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
