package com.example.administrator.on_off_button;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/1/5.
 */
public class PPTCircleView extends View{
    private int width =0,height;
    private float radius, end = 0;
    private Paint textPaint,outsideCirclePaint,insideCirclePaint,middleCirclePaint;
    public PPTCircleView(Context context) {
        super(context);
    }

    public PPTCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize((float)(20));

        outsideCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outsideCirclePaint.setStrokeWidth(20);
        outsideCirclePaint.setColor(Color.BLUE);

        insideCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        insideCirclePaint.setStrokeWidth(40);
        insideCirclePaint.setColor(Color.YELLOW);

        middleCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        middleCirclePaint.setStrokeWidth(20);
        middleCirclePaint.setColor(Color.RED);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        width = getMeasuredWidth();
//        height = getMeasuredHeight();
//        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        setEnd(end);
        RectF rectF = new RectF(50,50,250,250);
        canvas.drawArc(rectF, 0, end, true, outsideCirclePaint);
        canvas.drawCircle(150, 150, 70, insideCirclePaint);
        float width = textPaint.measureText("dasd");
        canvas.drawText("hello", 150 - width / 2, 150, textPaint);
        postInvalidateDelayed(1000);
    }


    public void setEnd(float end) {
        if(end == 360){
            this.end = 0;
        }else {
            this.end = end + 45;
        }
    }

    public  float getEnd(){
        return end;
    }
}
