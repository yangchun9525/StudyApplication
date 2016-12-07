package com.example.administrator.on_off_button;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.widget.ImageView;
/**
 * Created by Administrator on 2016/1/11.
 */
public class TestMatrixImageView extends ImageView{
    private Matrix mMatrix;
    private Bitmap mBitmap;
    public TestMatrixImageView(Context context) {
        super(context);
        mMatrix=new Matrix();
        mBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("---> onDraw");
        //画原图
        canvas.drawBitmap(mBitmap, 0, 0, null);
        //画经过Matrix变化后的图
        canvas.drawBitmap(mBitmap, mMatrix, null);
        super.onDraw(canvas);
    }
    @Override
    public void setImageMatrix(Matrix matrix) {
        System.out.println("---> setImageMatrix");
        this.mMatrix.set(matrix);
        super.setImageMatrix(matrix);
    }

    public Bitmap getBitmap(){
        System.out.println("---> getBitmap");
        return mBitmap;
    }

}