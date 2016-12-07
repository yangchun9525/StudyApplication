package com.example.administrator.on_off_button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/2/21.
 *
 * Reflect:倒影
 */
public class ReflectView extends View {
    private Bitmap mSrcBitmap;
    private Bitmap mReflectBitmap;
    private Paint mPaint;
    public ReflectView(Context context) {
        super(context);
        initView();
    }

    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        mSrcBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
        mPaint = new Paint();
        //0XDD000000,0X10000000,这是倒影效果，将颜色改为透明度
        mPaint.setShader(new LinearGradient(0,mSrcBitmap.getHeight(),0,mSrcBitmap.getHeight()*1.4F, 0XDD000000,0X10000000, Shader.TileMode.CLAMP));
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        Matrix matrix = new Matrix();
        matrix.setScale(1,-1);//x轴对称
        mReflectBitmap = Bitmap.createBitmap(mSrcBitmap,0,0,mSrcBitmap.getWidth(),mSrcBitmap.getHeight(),matrix,true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap,0,0,null);
        canvas.drawBitmap(mReflectBitmap, 0, mSrcBitmap.getHeight(), null);
        canvas.drawRect(0, mSrcBitmap.getHeight(),mSrcBitmap.getWidth(),mSrcBitmap.getHeight()*2,mPaint);
    }
}
