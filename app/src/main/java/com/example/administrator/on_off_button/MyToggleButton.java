package com.example.administrator.on_off_button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2015/11/3.
 */
public class MyToggleButton extends View implements View.OnClickListener, View.OnTouchListener {

    //背景图片
    private Bitmap backgroundBitmap;
    private Bitmap slideBtn;
    private Paint paint;

    private int slideBtn_left;

    /**
     * 在代码里面创建对象的时候，使用次构造方法
     *
     * @param context
     */
    public MyToggleButton(Context context) {
        super(context);
    }

    /**
     * 在布局文件中声明的view，创建是由系统自动调用
     *
     * @param context
     * @param attrs
     */
    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        //初始化图片
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        slideBtn = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);

        paint = new Paint();
        paint.setAntiAlias(true);//打开抗锯齿

        setOnClickListener(this);
        setOnTouchListener(this);
    }

    /**
     * view 对象显示的屏幕上，有几个重要步骤：
     * 1.构造方法创建对象
     * 2.测量view的大小  onMeasure(int,int)
     * 3.确定view的位置，view自身有一些建议权，决定权在父view手中.
     * onLayout(); 在view中不常用，viewgroup中常用
     * 4.绘制view的内容 onDraw(Canvas)
     */


    /**
     * 测量尺寸时的回调方法
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置当前view的大小，单位都是像素
        setMeasuredDimension(backgroundBitmap.getWidth(), backgroundBitmap.getHeight());
    }

    //确定位置的时候调用此方法
    //自定义view的时候，作用不大
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//    }

    //记录开关状态
    private boolean currState = false;

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("test onDraw", "1111111");
//        super.onDraw(canvas);
        //绘制背景
        canvas.drawBitmap(backgroundBitmap, 0, 0, paint);

        //绘制可滑动的按钮
        canvas.drawBitmap(slideBtn, slideBtn_left, 0, paint);
    }

    private boolean isDrag = false;

    @Override
    /**
     * onclick 事件在view.onTouchEvent中被解析
     * 系统对onclick事件的解析，过于简陋，只要有down事件和up事件，系统即认为了click事件
     * 如果点击下去没有发生移动，则出发点击事件，否则，出发触摸事件
     */
    public void onClick(View v) {
        if (!isDrag) {
            currState = !currState;
            //刷新当前状态
            flushState();
        }
    }

    private void flushState() {
        if (currState) {
            slideBtn_left = backgroundBitmap.getWidth() - slideBtn.getWidth();
        } else {
            slideBtn_left = 0;
        }

        /**
         * 刷新当前view，导致onDraw方法的执行
         */
        invalidate();
    }

    //down事件的x值
    private int firstX;
    //touch事件的x值
    private int lastX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("yc-2222222222", "onTouchEvent");
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrag = false;
                firstX = lastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(firstX - event.getX()) > 10) {
                    isDrag = true;
                }
                //计算手指在屏幕上移动的距离
                int dis = (int) (event.getX() - lastX);
                lastX = (int) event.getX();

                //根据手指移动的距离，改变slideBtn_left 的值
                Log.i("slideBtn_left:", "" + slideBtn_left);
                slideBtn_left = slideBtn_left + dis;
                break;
            case MotionEvent.ACTION_UP:
                int maxLeft = backgroundBitmap.getWidth() - slideBtn.getWidth();
                //在发生拖动的情况下，根据最后的位置，判断当前开关的状态
                if (isDrag) {
                    if (slideBtn_left < backgroundBitmap.getWidth() / 4) {
                        slideBtn_left = 0;
                    } else {
                        slideBtn_left = maxLeft;
                    }
                }
                break;
        }
        flushView();

        return true;
    }

    /**
     * 刷新视图
     */
    private void flushView() {
        /**
         * 对slideBtn_left的值进行判断，使其出现在合理的地方
         * 0<=slideBtn_left<=maxLeft
         */
        //slideBtn_left左边界最大值
        int maxLeft = backgroundBitmap.getWidth() - slideBtn.getWidth();
        //确保slideBtn_left >=0
        slideBtn_left = (slideBtn_left > 0) ? slideBtn_left : 0;
        //确保slideBtn_left <=maxLeft
        slideBtn_left = (slideBtn_left < maxLeft) ? slideBtn_left : maxLeft;
        invalidate();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("yc-1111111111111", "view-------onTouch");
        return false;
    }
}
