package com.example.administrator.on_off_button.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.administrator.on_off_button.R;
/**
 * Created by Administrator on 2016/3/6.
 */
public class ValueAnmiationActivity extends Activity{
    private int screenHeight;
    private int screenWidth;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_value_anmiation);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;

        imageView = (ImageView) findViewById(R.id.im);
    }

    public void clean(View view) {
        imageView.setTranslationX(320);
        imageView.setTranslationY(600);
    }

    public void animatorXML(View view) {
        // 加载动画
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scale);
        imageView.setPivotX(0);
        imageView.setPivotY(0);
        //显示的调用invalidate
        imageView.invalidate();
        anim.setTarget(imageView);
        anim.start();
    }

    public void freefall(View view) {
        final ValueAnimator animator = ValueAnimator.ofFloat(0, screenHeight
                - imageView.getHeight());
        animator.setTarget(view);
        //反弹插补器，反弹效果
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(1000).start();
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                imageView.setTranslationY(value);
            }
        });
    }

    public void setParabola(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha",
                1.0f, 0f);
        animator.setDuration(1000);
        animator.start();
//        ObjectAnimator anim = ObjectAnimator//
//                .ofFloat(view, "yc", 0.0F, 1.0F)//
//                .setDuration(1500);//
//        anim.start();
//        anim.addUpdateListener(new AnimatorUpdateListener()
//        {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation)
//            {
//                float cVal = (Float) animation.getAnimatedValue();
//                imageView.setTranslationX(cVal * screenHeight);
//                imageView.setTranslationY(cVal * cVal * 0.5f * screenHeight * 4f
//                        * 0.5f);
//            }
//        });
    }
    public void animatorSet(View view){
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "scaleX",
                1f, 2f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "scaleY",
                1f, 2f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView,
                "translationY", 0f, 500f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(animator1, animator2, animator3);
        set.start();
    }
    public void parabola(View view) {
        ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                Log.i("yc-fraction", fraction+"");
                if(fraction < 1) {
                    return fraction * screenWidth;
                }else {
                    return fraction * screenWidth - imageView.getHeight();
                }
            }
        }, 0);
//        ValueAnimator animator = ValueAnimator.ofObject(
//                new TypeEvaluator<PointF>() {
//                    //evaluate评价,类似于ObjectAnimator，p.y 抛物线的y轴运行轨迹
//                    @Override
//                    public PointF evaluate(float fraction, PointF arg1,
//                                           PointF arg2) {
//                        PointF p = new PointF();
//                        p.x = fraction * screenWidth;
//                        p.y = fraction * fraction * 0.5f * screenHeight * 4f
//                                * 0.5f;
//                        return p;
//                    }
//                }, new PointF(0, 0));
        animator.setDuration(800);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
//                PointF p = (PointF) animator.getAnimatedValue();
//                imageView.setTranslationX(p.x);
//                imageView.setTranslationY(p.y);
                float p = (float) animator.getAnimatedValue();
                imageView.setTranslationX(p);
            }
        });
    }
}
