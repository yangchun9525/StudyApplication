package com.example.administrator.on_off_button;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/12/20.
 */
public class HomePageLayout extends LinearLayout {

    private View mFirstView,mSecondView,mThirdView;
    private ViewDragHelper mViewDragHelper;
    private int mTop;
    //在这里指定是哪个view可以进行滑动
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback(){
        //何时开始检测触摸事件
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //如果当前触摸的child是mMainView时开始检测
            return mSecondView == child;
        }
        /**
         * 如果要实现滑动方法，则必须实现下列两个方法，分别对应水平和垂直方向的滑动
         * left表示移动的距离，dx表示偏移量
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return 0;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            final int topBound = getPaddingTop();
            final int bottomBound = getHeight() - mSecondView.getHeight() - mSecondView.getPaddingBottom();

            final int newTop = Math.min(Math.max(top, topBound), bottomBound);
            return newTop;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            Log.i("yc-changeg", top+","+dy+","+mSecondView.getMeasuredHeight()+","+mThirdView.getMeasuredHeight());
            mTop = top;
            requestLayout();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return mDragRange;
        }
    };
    public HomePageLayout(Context context) {
        super(context);
        init(context);
    }

    public HomePageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomePageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mViewDragHelper = ViewDragHelper.create(this,callback);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("yc-flag", mViewDragHelper.shouldInterceptTouchEvent(ev) +"");
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将拦截事件传递给ViewDragHelper，此操作必不可少
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mFirstView = getChildAt(0);
        mSecondView = getChildAt(1);
        mThirdView = getChildAt(2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private int mDragRange;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int parentViewHeight = getHeight();
        int dragViewHeight = mSecondView.getMeasuredHeight();
        mDragRange = parentViewHeight - dragViewHeight;
        Log.i("yc-mTop",mTop+"");
        if(mTop != 0) {
            mSecondView.layout(0, mTop, r, mTop + mSecondView.getMeasuredHeight());
            mThirdView.layout(0, mSecondView.getMeasuredHeight() - mTop, r, b-mTop);
        }
    }

    @Override
    public void computeScroll() {
//        super.computeScroll();
        if(mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
