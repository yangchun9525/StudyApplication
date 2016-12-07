package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.on_off_button.ImageMatrixView;
import com.example.administrator.on_off_button.R;

/**
 * Created by Administrator on 2016/2/27.
 */
public class ImageMatrixActivity extends Activity{
    private EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9;
    private Float str1,str2,str3,str4,str5,str6,str7,str8,str9;
    private float[] mMarArray = new float[9];
    private ImageMatrixView imageMatrixView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_matrix);
        imageMatrixView = (ImageMatrixView) findViewById(R.id.iv);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
        ed5 = (EditText) findViewById(R.id.ed5);
        ed6 = (EditText) findViewById(R.id.ed6);
        ed7 = (EditText) findViewById(R.id.ed7);
        ed8 = (EditText) findViewById(R.id.ed8);
        ed9 = (EditText) findViewById(R.id.ed9);
    }

    public void change(View view) {
        str1 = Float.parseFloat(ed1.getText().toString());
        str2 = Float.parseFloat(ed2.getText().toString());
        str3 = Float.parseFloat(ed3.getText().toString());
        str4 = Float.parseFloat(ed4.getText().toString());
        str5 = Float.parseFloat(ed5.getText().toString());
        str6 = Float.parseFloat(ed6.getText().toString());
        str7 = Float.parseFloat(ed7.getText().toString());
        str8 = Float.parseFloat(ed8.getText().toString());
        str9 = Float.parseFloat(ed9.getText().toString());
        mMarArray[0] = str1;
        mMarArray[1] = str2;
        mMarArray[2] = str3;
        mMarArray[3] = str4;
        mMarArray[4] = str5;
        mMarArray[5] = str6;
        mMarArray[6] = str7;
        mMarArray[7] = str8;
        mMarArray[8] = str9;
        Matrix matrix = new Matrix();
        matrix.setValues(mMarArray);
//    	matrix.setTranslate(150, 150);
//    	matrix.setScale(2, 2);
//    	matrix.postTranslate(200, 200);//顺序显示，使用post方法
        imageMatrixView.setImageMatrix(matrix);
    }

    public void restore(View view){

    }
}
