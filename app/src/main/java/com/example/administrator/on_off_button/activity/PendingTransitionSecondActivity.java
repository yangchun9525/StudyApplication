package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Window;

import com.example.administrator.on_off_button.R;

/**
 * Created by yangchun on 2016-12-7.
 */
public class PendingTransitionSecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置SecondActivity的进入动画和退出动画
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Slide());
        }
        setContentView(R.layout.activity_pending_transition_second);
    }
}
