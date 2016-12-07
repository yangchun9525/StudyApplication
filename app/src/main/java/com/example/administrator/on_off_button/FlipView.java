package com.example.administrator.on_off_button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/2/24.
 */
public class FlipView extends View{
    private Bitmap mBgBitmap,mFgBitmap;
    private Paint mPaint;
    private int width,height;
    private int WIDTH = 20;
    private int HEIGHT =50;
    //所有坐标点的个数
    private int count = (WIDTH + 1)*(HEIGHT + 1);
    //记录所有点的坐标,{x0,y0,x1,y1,x2,y2......}
    private float[] verts = new float[count * 2];
    //记录所有点的原始的坐标信息,{x0,y0,x1,y1,x2,y2......}
    private float[] origs = new float[count * 2];
    private float k = 0.1f;
    public FlipView(Context context) {
        super(context);
        initValue();
    }

    public FlipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initValue();
    }

    public FlipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValue();
    }
    private void initValue(){
        mBgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
        mFgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fg);
        mPaint = new Paint();
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        int index = 0;
        for (int i = 0;i<HEIGHT + 1;i++){
            float fy =height * i /HEIGHT;
            for (int j = 0;j<WIDTH +1 ;j++){
                float fx =width * j /WIDTH;
                verts[index*2+0] = fx;
                verts[index*2+1] = fy;

                origs[index*2+0] = fx;
                origs[index*2+1] = fy;
                index ++;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(initBitmaps(mBgBitmap), 0, 0, null);
//        canvas.drawBitmapMesh(initBitmaps(mFgBitmap), 0, 0, null);
        for (int i = 0;i<HEIGHT + 1;i++){
            for (int j = 0;j<WIDTH +1 ;j++){
                //所有X坐标
                verts[2 * (i*(WIDTH + 1) + j) + 0] = verts[2 * (i*(WIDTH + 1) + j) + 0] + 150 * k;
                verts[2 * (i*(WIDTH + 1) + j) + 1] += 0;
            }
        }
//        k += 0.01F;
        //0表示vertp[]进行偏移
        canvas.drawBitmapMesh(initBitmaps(mFgBitmap), WIDTH, HEIGHT, verts, 0, null, 0, null);
        invalidate();
    }

    private Bitmap initBitmaps(Bitmap bitamp) {
        return Bitmap.createScaledBitmap(bitamp, width, height, true);
    }
}
