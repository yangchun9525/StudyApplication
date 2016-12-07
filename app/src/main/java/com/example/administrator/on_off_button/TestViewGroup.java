package com.example.administrator.on_off_button;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by Administrator on 2016/1/10.
 */
public class TestViewGroup extends ViewGroup{
    private int width,height;
    private int mLastY,mStart,mEnd;
    private Scroller mScroller;
    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        init();
    }

    private void init() {
        //得到屏幕的高度和宽度
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
//        setViewGroupHeight();
    }

    //在viewgroup之前，先放置好它的子view，使用遍历的方式来通知子view对自身进行测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i =0;i < count;i++){
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 获取了viewgroup的高度之后，通过遍历来设定每个子view需要放置的位置，
     * 调用onLayout方法，并将具体的位置当作参数传递过去即可
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        /**
         * 对子view进行放置位置的设定。让每个子view都显示完整的一屏。
         * 这样viewgroup的高度就为 屏幕高度*子view个数
         */
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = height*childCount;
        setLayoutParams(mlp);
        for(int i =0;i<childCount;i++){
            View child = getChildAt(i);
            if(child.getVisibility() != GONE){
                child.layout(l,i*height, r, (i+1)*height);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                //获取当前View 滑动到的位置
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                if(getScaleY() < 0){
                    Log.i("test 1111","1111");
                    dy = 0;
                }
                if(getScaleY()> getHeight() - height){
                    Log.i("test 2222","2222");
                    dy = 0;
                }
                Log.i("test getScaleY",getScaleY()+"");
                Log.i("test dy",dy+"");
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                if(dScrollY > 0){
                    if(dScrollY < height/3){
                        //startScroll是最后移动到什么位置，之后会触发computeScroll方法，来达到移动的效果
                        mScroller.startScroll(0, getScrollY(), 0,  -dScrollY);
                    }else {
                        mScroller.startScroll(0, getScrollY(), 0,  height -dScrollY);
                    }
                }else {
                    if(-dScrollY < height/3){
                        mScroller.startScroll(0, getScrollY(), 0,  -dScrollY);
                    }else {
                        mScroller.startScroll(0, getScrollY(), 0,  -height -dScrollY);
                    }
                }
                Log.i("test","ACTION_UP");
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //如果滑动未完成
        if(mScroller.computeScrollOffset()){
            Log.i("test","computeScroll");
            //http://blog.csdn.net/bigconvience/article/details/26697645
            //scrollTo直接移动到某个位置，scrollBy是移动的位置
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
