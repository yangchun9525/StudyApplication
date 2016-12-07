package com.example.administrator.on_off_button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/1/6.
 */
public class RectView extends View{
    private LinearGradient mLinearGradient;
    private int count = 10;
    private int width = 0,mRectWidth = 10,offset = 0,mRectHeight;
    private float currentHeight;
    private Paint paint;
    public RectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectView(Context context) {
        super(context);
//        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
//        if(width == 0){
//            width = getMeasuredWidth();
//            mRectHeight = getMeasuredHeight();
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        Log.i("test rectView","1111111");
        super.onDraw(canvas);
        for(int i=0;i<count;i++){
            double random = Math.random();
            currentHeight = (float) (mRectHeight * random);
            canvas.drawRect((float)(width * 0.4 /2 + mRectWidth*i + offset),currentHeight,(float)(width * 0.4 /2 + mRectWidth*(i+1)),mRectHeight,paint);
            postInvalidateDelayed(3000);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        Log.i("test onSizeChanged", "2222222222");
        width = getMeasuredWidth();
        mRectHeight = getMeasuredHeight();
        mRectWidth = (int)(width*0.6 / count);
        /**
         * // ����LinearGradient�����ý�����ɫ����
         // ��һ��,�ڶ���������ʾ������� ������������յ��ڶԽǵ�����λ��
         // ������,���ĸ�������ʾ�����յ�
         // �����������ʾ������ɫ
         // ��������������Ϊ��,��ʾ����,ֵΪ0-1 new float[] {0.25f, 0.5f, 0.75f, 1 }
         // ������ǿյģ���ɫ���ȷֲ������ݶ��ߡ�
         // ���߸���ʾƽ�̷�ʽ
         // CLAMP�ظ����һ����ɫ�����,���������һ�����ص�
         // MIRROR�ظ���ɫ��ͼ��ˮƽ��ֱ�����Ѿ���ʽ�����з�תЧ��
         // REPEAT�ظ���ɫ��ͼ��ˮƽ��ֱ����
         ������Ⱦ1,2��ʾԲ�ģ�3��ʾ�뾶��4��5��6������һ��
         new RadialGradient();
         new LinearGradient(0 ,0, mViewWidth, 0, new int[]{Color.BLUE, Color.RED, Color.BLACK}, null, Shader.TileMode.CLAMP)
         */
        mLinearGradient = new LinearGradient(0,0,mRectWidth,mRectHeight, Color.YELLOW,Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(mLinearGradient);
    }
}
