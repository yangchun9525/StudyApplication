package com.example.administrator.on_off_button;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/14.
 */
public class ViewDragViewGroup extends FrameLayout implements View.OnClickListener {
    private TextView mTextView;
    private ImageView mImageView;

    private int width;
    private View mMainView,mMenuView;
    private ViewDragHelper mViewDragHelper;
    //在这里指定是哪个view可以进行滑动
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback(){
        //何时开始检测触摸事件
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //如果当前触摸的child是mMainView时开始检测
            return mMainView == child;
        }
        /**
         * 如果要实现滑动方法，则必须实现下列两个方法，分别对应水平和垂直方向的滑动
         * left表示移动的距离，dx表示偏移量
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
//            mMenuView.setVisibility(VISIBLE);
            if(mMainView.getLeft() < 300){
                //关闭菜单
                //相当于scroller中的startScroll方法
                mViewDragHelper.smoothSlideViewTo(mMainView,0,0);
                ViewCompat.postInvalidateOnAnimation(ViewDragViewGroup.this);
            }else {
                mViewDragHelper.smoothSlideViewTo(mMainView,300,0);
                ViewCompat.postInvalidateOnAnimation(ViewDragViewGroup.this);
            }
        }
    };
    public ViewDragViewGroup(Context context) {
        super(context);
    }

    public ViewDragViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
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
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
//        mTextView = (TextView) mMenuView.findViewById(R.id.tv);
//        mImageView = (ImageView) mMenuView.findViewById(R.id.iv);
//        mTextView.setOnClickListener(this);
//        mImageView.setOnClickListener(this);
    }

    public View getView(int id,View parentView){
        return parentView.findViewById(id);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = mMenuView.getMeasuredWidth();
    }

    @Override
    public void computeScroll() {
//        super.computeScroll();
        if(mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv:
                Log.i("yc-text","click text");
                ((TextView) v).setText("click text");
                postInvalidate();
                break;
            case R.id.iv:
                Log.i("yc-iv","click picture");
                mTextView.setText("click picture");
                postInvalidate();
                break;
        }
    }
}
