package com.example.administrator.on_off_button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/1.
 */
public class MyTextView  extends TextView{
    private Paint paint1,paint2,paint3;
    private int mViewWidth = 0;
    private LinearGradient mLinearGradient;
    private Matrix matrix;
    private int mTranslate = 0;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initValue();
    }

    private void initValue() {
        paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        paint1.setStyle(Paint.Style.FILL);

        paint2 = new Paint();
        paint2.setColor(Color.YELLOW);
        paint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawRect( 0, 0, getMeasuredWidth(), getMeasuredHeight(), paint1);
//        canvas.drawRect( 10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, paint2);
//        canvas.save();
//        canvas.translate(10, 0);
//        super.onDraw(canvas);
//        canvas.restore();
        super.onDraw(canvas);
        //matrix相关学习
        if(matrix != null){
            mTranslate += mViewWidth / 5;
//            Log.i("test mViewWidth", mViewWidth+""+",mTranslate:"+mTranslate);
            if(mTranslate > 2 * mViewWidth){
                mTranslate = -mViewWidth;
            }
            matrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            if(mViewWidth > 0){
                paint3 = getPaint();
                //LinearGradient 线性渐变
                mLinearGradient = new LinearGradient(0 ,0, mViewWidth, 0, new int[]{Color.BLUE, Color.RED, Color.BLACK}, null, Shader.TileMode.CLAMP);
                paint3.setShader(mLinearGradient);
                matrix = new Matrix();
            }
        }
    }
}
