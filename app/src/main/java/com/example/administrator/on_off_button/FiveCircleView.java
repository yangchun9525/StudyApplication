package com.example.administrator.on_off_button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/2/23.
 */
public class FiveCircleView extends View{
    private Paint paint;
    private int width,height;
    public FiveCircleView(Context context) {
        super(context);
        initView();
    }

    public FiveCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FiveCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        //得到屏幕的高度和宽度
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        drawCenterCircle(canvas);
    }

    private void drawCenterCircle(Canvas canvas) {
        canvas.drawCircle(width / 2, height / 2, 50, paint);
        float startX = (float) (width / 2 - 50 * 2 * Math.sin(Math.PI / 2));
        float startY = (float) (height / 2 - 50 * 2 * Math.sin(Math.PI / 2));
        float endX = (float) (width / 2);
        float endY = (float) (height / 2);

        float startX2 = (float) (width / 2 + 50 * 2 * Math.sin(Math.PI / 2));
        float startY2 = (float) (height / 2 - 50 * 2 * Math.sin(Math.PI / 2));

        float startX3 = (float) (width / 2 - 50 * 2 * Math.sin(Math.PI / 2));
        float startY3 = (float) (height / 2 + 50 * 2 * Math.sin(Math.PI / 2));

        float startX4 = (float) (width / 2 + 50 * 2 * Math.sin(Math.PI / 2));
        float startY4 = (float) (height / 2 + 50 * 2 * Math.sin(Math.PI / 2));
        canvas.drawLine(startX,startY, endX,endY, paint);
        canvas.drawLine(startX2,startY2, endX,endY, paint);
        canvas.drawLine(startX3,startY3, endX,endY, paint);
        canvas.drawLine(startX4,startY4, endX,endY, paint);

        float circleX1 = (float) (width / 2 - 130 *  Math.sin(Math.PI / 2));
        float circleY1 = (float) (height / 2 - 130 *  Math.sin(Math.PI / 2));

        float circleX2 = (float) (width / 2 + 130 *  Math.sin(Math.PI / 2));
        float circleY2 = (float) (height / 2 - 130 *  Math.sin(Math.PI / 2));

        float circleX3 = (float) (width / 2 - 130 *  Math.sin(Math.PI / 2));
        float circleY3 = (float) (height / 2 + 130 *  Math.sin(Math.PI / 2));

        float circleX4 = (float) (width / 2 + 130 *  Math.sin(Math.PI / 2));
        float circleY4 = (float) (height / 2 + 130 *  Math.sin(Math.PI / 2));
        canvas.drawCircle(circleX1, circleY1, 50, paint);
        canvas.drawCircle(circleX2, circleY2, 50, paint);
        canvas.drawCircle(circleX3, circleY3, 50, paint);
        canvas.drawCircle(circleX4, circleY4, 50, paint);
    }
}
