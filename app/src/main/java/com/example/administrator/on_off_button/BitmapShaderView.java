package com.example.administrator.on_off_button;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/2/21.
 */
public class BitmapShaderView extends View {
    private Bitmap mBitmap;
    private Paint mPaint;
    private BitmapShader mBitmapShader;
    public BitmapShaderView(Context context) {
        super(context);
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mBitmapShader);
//        canvas.drawCircle(300, 200, 300, mPaint);
        RectF rectF = new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        canvas.drawRoundRect(rectF, 90, 45, mPaint);
    }
}
