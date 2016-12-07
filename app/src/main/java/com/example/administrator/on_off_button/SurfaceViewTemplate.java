package com.example.administrator.on_off_button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2016/2/28.
 * SurfaceView通过启用一个子线程来进行画面的绘制
 */
public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    //子线程标志
    private boolean isDrawing;
    private int x,y;
    private Path mPath;
    private Paint mPaint;
    public SurfaceViewTemplate(Context context) {
        super(context);
        init();
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);

    }
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (isDrawing){
            draw();
//            x += 1;
//            y = (int)(100*Math.sin(x * 2 * Math.PI / 180) + 400);
//            mPath.lineTo(x,y);
        }
        long end = System.currentTimeMillis();
        if(end - start < 100){
            try {
                Thread.sleep(100 - end + start);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw() {
        try {
            /*
                获得当前canvas绘图对象，获取到的canvas对象还是继续上次的canvas对象，而不是一个新的
                对象，因此，之前的绘制操作都将被保留，如需擦除，则可以在绘制前,通过drawColor()方法进行清屏
             */
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath,mPaint);
            //draw something
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(mCanvas != null){
                //通过此unlockCanvasAndPost方法将画布内容进行提交
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
