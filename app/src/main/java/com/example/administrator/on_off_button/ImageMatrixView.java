package com.example.administrator.on_off_button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
/**
 * Created by Administrator on 2016/2/27.
 */
public class ImageMatrixView extends View{

    private Bitmap mBitmip;
    private Matrix mMatrix;
    public ImageMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ImageMatrixView(Context context) {
        super(context);
        initView();
    }

    public ImageMatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mBitmip = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        setImageMatrix(new Matrix());
    }

    public void setImageMatrix(Matrix matrix){
        mMatrix = matrix;
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmip, 0, 0, null);//原图
        canvas.drawBitmap(mBitmip, mMatrix, null);//锟皆憋拷图
    }
}
