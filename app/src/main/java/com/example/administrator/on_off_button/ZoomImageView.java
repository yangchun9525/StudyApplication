package com.example.administrator.on_off_button;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/3/10.
 *
 * OnGlobalLayoutListener,当全局布局完成后会调用此接口
 */
public class ZoomImageView extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener, ScaleGestureDetector.OnScaleGestureListener,OnTouchListener {
    private boolean mOnce = false;
    //初始化时缩放的值也是最小的缩放值
    private float mInitScale;
    //双击放大是到达的值
    private float mMidScale;
    //放大的极限
    private float mMaxScale;
    //捕获用户多点触控时缩放的比例
    private ScaleGestureDetector mScaleGestureDetector;
    private Matrix matrix;
    //----------自由移动
    //记录上一个多点触控的数量
    private int mLastPointCount;
    private float mLastX;
    private float mLastY;
    //如果超过这个值才能移动控件
    private int mTouchSlop;
    private boolean isCanDrag;

    private boolean isCheckLeftAndRight;
    private boolean isCheckTopAndBottom;

    //---------------双击放大与缩小
    private GestureDetector mGestureDetector;
    private boolean isAutoScale;
    public ZoomImageView(Context context) {
        this(context,null);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        matrix = new Matrix();
        setScaleType(ScaleType.MATRIX);
        mScaleGestureDetector= new ScaleGestureDetector(context,this);
        setOnTouchListener(this);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if(isAutoScale){
                    return true;
                }
                float x = e.getX();
                float y = e.getY();
                if(getScale() < mMidScale){
//                    matrix.postScale(mMidScale / getScale(), mMidScale / getScale(), x, y);
//                    setImageMatrix(matrix);
                    postDelayed(new AutoScaleRunnable(mMidScale, x, y), 16);
                    isAutoScale = true;
                }else {
//                    matrix.postScale(mInitScale / getScale(), mInitScale / getScale(), x, y);
//                    setImageMatrix(matrix);
                    postDelayed(new AutoScaleRunnable(mInitScale, x, y), 16);
                    isAutoScale = true;
                }
                return true;
            }
        });
    }

    /**
     * 自动放大缩小
     */
    private class AutoScaleRunnable implements Runnable{
        //缩放的目标值
        private float mTargetScale;
        //缩放的中心点
        private float x;
        private float y;
        //放大是以1.07f的比例放大
        private final float BIGGER = 1.07f;
        private final float SMALL = 0.93f;

        private float tmpScale;
        public AutoScaleRunnable(float mTargetScale,float x,float y){
            this.mTargetScale = mTargetScale;
            this.x = x;
            this.y = y;
            if(getScale() < mTargetScale){
                tmpScale = BIGGER;
            }
            if(getScale() > mTargetScale){
                tmpScale = SMALL;
            }
        }
        @Override
        public void run() {
            matrix.postScale(tmpScale, tmpScale, x, y);
            checkBorderAndCenterWhenScale();
            setImageMatrix(matrix);

            float currentScale = getScale();
            if((tmpScale > 1.0f) && currentScale < mTargetScale || (tmpScale<1.0f  && currentScale > mTargetScale) ){
                postDelayed(this, 16);
            }else {
                //设置为目标值
                float scale = mTargetScale / currentScale;
                matrix.postScale(scale, scale, x, y);
                checkBorderAndCenterWhenScale();
                setImageMatrix(matrix);
                isAutoScale = false;
            }
        }
    }
    //当view在window上出现的时候调用
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
    //当view在window上分离的时候调用
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    /**
     * 获取imageview加载完成的图片
     */
    @Override
    public void onGlobalLayout() {
        if(!mOnce){
            //得到控件的宽和高
            int width = getWidth();
            int height = getHeight();
            //得到图片以及图片的宽和高
            Drawable d = getDrawable();
            if(d == null){
                return;
            }
            int ivWidth = d.getIntrinsicWidth();
            int ivHeight = d.getIntrinsicHeight();

            //此参数表示放大缩小的比例
            float scale = 1.0f;
            /**
             * 如果图片的宽度大于控件的宽度，但是高度小于控件高度，则进行缩放
             * 如果图片的高度大于控件的高度，但是宽度小于控件宽度，则进行缩放
             * 如果图片的高度大于控件的高度，但是宽度大于控件宽度，则进行缩放
             * 如果图片的高度小于控件的高度，但是宽度小于控件宽度，则进行放大
             */
            if(ivWidth > width && ivHeight < height){
                scale = width * 1.0f / ivWidth;
            }

            if(ivHeight > height && ivWidth < width){
                scale = height * 1.0f / ivHeight;
            }

            if(ivHeight > height && ivWidth > width){
                scale = Math.min(height * 1.0f / ivHeight,width * 1.0f / ivWidth);
            }

            if(ivHeight < height && ivWidth < width){
                scale = Math.min(height * 1.0f / ivHeight, width * 1.0f / ivWidth);
            }

            //得到初始化是缩放的比例
            mInitScale = scale;
            mMaxScale = mInitScale * 4;
            mMidScale = mInitScale * 2;

            //移动图片，将图片移动至当前控件的中心
            int dx = getWidth() / 2 - ivWidth / 2;
            int dy = getHeight() / 2 - ivHeight / 2;

            matrix.postTranslate(dx, dy);
            matrix.postScale(mInitScale, mInitScale, width / 2, height/2);
            setImageMatrix(matrix);

            mOnce = true;
        }
    }
    //获取当前图片的缩放值
    public float getScale(){
        float[] values = new float[9];
        matrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scale = getScale();
        //获取触摸的值
        float scaleFactor = detector.getScaleFactor();

        if(getDrawable() == null){
            return true;
        }
        //缩放范围的控制
        if((scale < mMaxScale && scaleFactor > 1.0f) || (scale > mInitScale && scaleFactor < 1.0f)){
            if(scaleFactor * scale < mInitScale){
                scaleFactor = mInitScale / scale;
//                scaleFactor = mInitScale;
            }

            if(scaleFactor * scale > mMaxScale){
                scaleFactor = mMaxScale / scale;
//                scaleFactor = mMaxScale;
            }

            matrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
            checkBorderAndCenterWhenScale();
            setImageMatrix(matrix);
        }
        return true;
    }

    /**
     * 获得图片放大缩小以后的宽和高
     * @return
     */
    private RectF getMatrixRectF(){
        Matrix matrix1 = matrix;
        RectF rectF = new RectF();
        Drawable d = getDrawable();
        if(d != null){
            rectF.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix1.mapRect(rectF);
        }
        return rectF;
    }
    //在缩放的时候进行边界控制和中心控制
    private void checkBorderAndCenterWhenScale() {
        RectF rectF = getMatrixRectF();
        //x,y方向的差值
        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();
        //缩放是出现边界检测，以防出现白边
        if(rectF.width() >= width){
            if(rectF.left > 0){
                deltaX = - rectF.left;
            }

            if(rectF.right < width){
                deltaX = width - rectF.right;
            }
        }

        if(rectF.height() >= height){
            if(rectF.top > 0){
                deltaY = - rectF.top;
            }

            if(rectF.bottom < height){
                deltaY = height - rectF.bottom;
            }
        }
        //如果宽度或者高度小于控件的宽或者高，让其居中
        if(rectF.width() < width){
            deltaX = width/2 - rectF.right + rectF.width() / 2;
        }
        if(rectF.height() < height){
            deltaY = height/2 - rectF.bottom + rectF.height() / 2;
        }
        matrix.postTranslate(deltaX,deltaY);
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(mGestureDetector.onTouchEvent(event)){
            return true;
        }
        mScaleGestureDetector.onTouchEvent(event);
        float x = 0;
        float y =0;
        //得到多点触控的数量
        int pointCount = event.getPointerCount();
        for (int i= 0;i < pointCount; i++){
            x += event.getX(i);
            y += event.getY(i);
        }
        //得到中心点的位置
        x = x / pointCount;
        y = y / pointCount;
        /**如果手指数量发生了变化，记录下上一次的坐标，
         * 如果一直是两根手指进行滑动的话，则不会执行这里，
         * 如果一开始是两根手指，后面是一根手指则会执行这里
         */
        if(mLastPointCount != pointCount){
            isCanDrag = false;
            mLastX = x;
            mLastY = y;
        }
        mLastPointCount = pointCount;
        //当按下屏幕的时候，如果控件的宽度宽过屏幕，则不需要进行拦截，否则进行拦截
        RectF rectF = getMatrixRectF();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(rectF.width() > getWidth() + 0.01|| rectF.height() > getHeight() + 0.01){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(rectF.width() > getWidth() + 0.01|| rectF.height() > getHeight() + 0.01){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                //记录下手指移动的偏移量
                float dx = x - mLastX;
                float dy = y - mLastY;
                if(!isCanDrag){
                    isCanDrag = isMoveAction(dx,dy);
                }
                if(isCanDrag){
                    if(getDrawable() != null){
                        isCheckLeftAndRight = isCheckTopAndBottom = true;
                        //如果宽度小于控件宽度，不允许横向移动
                        if(rectF.width() < getWidth()){
                            isCheckLeftAndRight = false;
                            dx = 0;
                        }
                        if(rectF.height() < getHeight()){
                            isCheckTopAndBottom = false;
                            dy = 0;
                        }
                        matrix.postTranslate(dx, dy);
                        checkBorderWhenTranslate();
                        setImageMatrix(matrix);
                    }
                }
                //最后将手指的触控点赋值给lastX
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_CANCEL:
                mLastPointCount = 0;
                break;
        }
        return true;
    }
    //检查当图片移动的时候是否超过边界
    private void checkBorderWhenTranslate() {
        RectF rectF = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;
        int width = getWidth();
        int height = getHeight();
        if(rectF.top > 0 && isCheckTopAndBottom){
            deltaY = - rectF.top;
        }
        if(rectF.bottom < height && isCheckTopAndBottom){
            deltaY = height - rectF.bottom;
        }
        if(rectF.left > 0 && isCheckLeftAndRight){
            deltaX = - rectF.left;
        }
        if(rectF.right < width && isCheckLeftAndRight){
            deltaX =  width - rectF.right;
        }
        matrix.postTranslate(deltaX, deltaY);
    }

    /**
     * 判断是否可以move
     * @param dx
     * @param dy
     * @return
     */
    private boolean isMoveAction(float dx, float dy) {
        return Math.sqrt(dx * dx + dy * dy) > mTouchSlop;
    }
}
