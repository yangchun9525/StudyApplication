package com.example.administrator.on_off_button.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.app.Activity;
import android.graphics.Matrix;

import com.example.administrator.on_off_button.TestMatrixImageView;

/**
 * Created by Administrator on 2016/1/11.
 */
public class MatrixActivity extends Activity {
    private TestMatrixImageView mTestMatrixImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTestMatrixImageView=new TestMatrixImageView(MatrixActivity.this);
        mTestMatrixImageView.setScaleType(ImageView.ScaleType.MATRIX);//??
        mTestMatrixImageView.setOnTouchListener(new TouchListenerImpl());
        setContentView(mTestMatrixImageView);
    }

    private class TouchListenerImpl implements OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction()==MotionEvent.ACTION_UP) {
                //1 测试平移
                testTranslate();
                //2 测试围绕图片中心点旋转
//                testRotate();
                //3 测试围绕原点旋转后平移
//                testRotateAndTranslate();
                //4 缩放
//                testScale();
                //5 水平倾斜
//                testSkewX();
                //6 垂直倾斜
                //testSkewY();
                //7 水平且垂直倾斜
                //testSkewXY();
                //8 水平对称
//                testSymmetryX();
                //9 垂直对称
                //testSymmetryY();
                //10 关于X=Y对称
//                testSymmetryXY();
            }
            return true;
        }

    }

    //平移
    private void testTranslate(){
        Matrix matrix=new Matrix();
        int width=mTestMatrixImageView.getBitmap().getWidth();
        int height=mTestMatrixImageView.getBitmap().getHeight();
        matrix.postTranslate(width, height);
        mTestMatrixImageView.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }
    //围绕图片中心点旋转
    private void testRotate(){
        Matrix matrix=new Matrix();
        int width=mTestMatrixImageView.getBitmap().getWidth();
        int height=mTestMatrixImageView.getBitmap().getHeight();
        matrix.postRotate(45f, width/2, height/2);
        matrix.postTranslate(width, height);
        mTestMatrixImageView.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }

    /**
     * preTranslate是指在setScale前,平移,postTranslate是指在setScale后平移
     */
    //围绕原点旋转后平移
    //注意以下三行代码的执行顺序:
    //matrix.setRotate(45f);
    //matrix.preTranslate(-width, -height);
    //matrix.postTranslate(width, height);
    //先执行matrix.preTranslate(-width, -height);
    //后执行matrix.setRotate(45f);
    //再执行matrix.postTranslate(width, height);
    private void testRotateAndTranslate() {
        Matrix matrix = new Matrix();
        int width = mTestMatrixImageView.getBitmap().getWidth();
        int height = mTestMatrixImageView.getBitmap().getHeight();
        matrix.setRotate(45f);
        matrix.preTranslate(-width, -height);
        matrix.postTranslate(width, height);
        mTestMatrixImageView.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }

    //缩放
    private void testScale() {
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 0.5f);
        mTestMatrixImageView.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }

    //水平倾斜
    private void testSkewX() {
        Matrix matrix = new Matrix();
        matrix.setSkew(0.5f, 0);
        mTestMatrixImageView.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }

    // 垂直倾斜
    private void testSkewY() {
        Matrix matrix = new Matrix();
        matrix.setSkew(0, 0.5f);
        mTestMatrixImageView.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }

    // 水平且垂直倾斜
    private void testSkewXY() {
        Matrix matrix = new Matrix();
        matrix.setSkew(0.5f, 0.5f);
        mTestMatrixImageView.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }

    // 水平对称--图片关于X轴对称
    private void testSymmetryX() {
        Matrix matrix = new Matrix();
        int height = mTestMatrixImageView.getBitmap().getHeight();
        float matrixValues[] = { 1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f };
        matrix.setValues(matrixValues);
        //若是matrix.postTranslate(0, height);
        //表示将图片上下倒置
        matrix.postTranslate(0, height*2);
        mTestMatrixImageView.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }

    // 垂直对称--图片关于Y轴对称
    private void testSymmetryY() {
        Matrix matrix = new Matrix();
        int width=mTestMatrixImageView.getBitmap().getWidth();
        float matrixValues[] = {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
        matrix.setValues(matrixValues);
        //若是matrix.postTranslate(width,0);
        //表示将图片左右倒置
        matrix.postTranslate(width*2, 0);
        mTestMatrixImageView.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);

    }

    // 关于X=Y对称--图片关于X=Y轴对称
    private void testSymmetryXY() {
        Matrix matrix = new Matrix();
        int width = mTestMatrixImageView.getBitmap().getWidth();
        int height = mTestMatrixImageView.getBitmap().getHeight();
        float matrixValues[] = { 0f, -1f, 0f, -1f, 0f, 0f, 0f, 0f, 1f };
        matrix.setValues(matrixValues);
        matrix.postTranslate(width*2, height*2);
        mTestMatrixImageView.setImageMatrix(matrix);
        showMatrixEveryValue(matrix);
    }

    //获取变换矩阵Matrix中的每个值
    private void showMatrixEveryValue(Matrix matrix){
        float matrixValues []=new float[9];
        matrix.getValues(matrixValues);
        for (int i = 0; i <3; i++) {
            String valueString="";
            for (int j = 0; j < 3; j++) {
                valueString=matrixValues[3*i+j]+"";
                System.out.println("第"+(i+1)+"行的第"+(j+1)+"列的值为"+valueString);
            }
        }
    }

}