package com.example.administrator.on_off_button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/2/21.
 * mesh:����
 */
public class MeshView extends View {
    //WIDTH��HEIGHT��ʾ�ֳɺ�������ĸ�ʽ����
    private int WIDTH = 20;
    private int HEIGHT = 200;
    //���������ĸ���
    private int count = (WIDTH + 1)*(HEIGHT + 1);
    //��¼���е������,{x0,y0,x1,y1,x2,y2......}
    private float[] verts = new float[count * 2];
    //��¼���е��ԭʼ��������Ϣ,{x0,y0,x1,y1,x2,y2......}
    private float[] origs = new float[count * 2];
    private Bitmap mBitmap;
    private float k = 0.1f;
    public MeshView(Context context) {
        super(context);
        initView();
    }

    public MeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
        float bmWidth = mBitmap.getWidth();
        float bmHeight = mBitmap.getHeight();
        int index = 0;
        for (int i = 0;i<HEIGHT + 1;i++){
            float fy =bmHeight * i /HEIGHT;
            for (int j = 0;j<WIDTH +1 ;j++){
                float fx =bmWidth * j /WIDTH;
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
        for (int i = 0;i<HEIGHT + 1;i++){
            for (int j = 0;j<WIDTH +1 ;j++){
                //����X����
                verts[2 * (i*(WIDTH + 1) + j) + 0] += 0;
                float offsetY = (float)Math.sin((float)j/WIDTH * 2 *Math.PI + k * 2 * Math.PI);
                verts[2 * (i*(WIDTH + 1) + j) + 1] = origs[2 * (i*(WIDTH + 1) + j) + 1] + 50 * offsetY;
            }
        }
        k += 0.01F;
        //0��ʾvertp[]����ƫ��
        canvas.drawBitmapMesh(mBitmap,WIDTH,HEIGHT,verts,0,null,0,null);
        invalidate();
    }
}
