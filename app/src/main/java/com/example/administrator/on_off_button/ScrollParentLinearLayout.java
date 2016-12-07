package com.example.administrator.on_off_button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by yangchun on 2016-12-6.
 */

public class ScrollParentLinearLayout extends LinearLayout{
    //移动的y减去第一次的y得到的最终值
    private int mYMove;
    //第一次按下的y值，每一次移动的时候的y值
    private int downY,moveY;

    private int i = 0;
    public ScrollParentLinearLayout(Context context) {
        super(context);
        initView();
    }

    public ScrollParentLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ScrollParentLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if(y - downY > 0){
                    mYMove = y - downY;
                    i += mYMove;
                    layout(getLeft(), getTop() + mYMove, getRight(), getBottom() + mYMove);
                }
                break;
            case MotionEvent.ACTION_UP:
                layout(getLeft(), getTop() - i, getRight(), getBottom() - i);
                i = 0;
                break;
        }
        return true;
    }

    boolean flag = false;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if(y - downY > 0){
                    mYMove = y - downY;
                    flag = true;
                }else {
                    flag = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return flag;
    }
}
