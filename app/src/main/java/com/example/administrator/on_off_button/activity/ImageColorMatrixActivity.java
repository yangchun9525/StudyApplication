package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.on_off_button.R;

/**
 * Created by Administrator on 2016/2/27.
 */
public class ImageColorMatrixActivity extends Activity {
    private EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,ed11,ed13,ed14,ed15,ed16,ed17,ed18,ed19,ed12,ed20;
    private Float str1,str2,str3,str4,str5,str6,str7,str8,str9,str11,str12,str13,str14,str15,str16,str17,str18,str19,str10,str20;
    private float[] mMarArray = new float[20];
    private Bitmap bitmap;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_color_matrix);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
        iv = (ImageView) findViewById(R.id.iv);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
        ed5 = (EditText) findViewById(R.id.ed5);
        ed6 = (EditText) findViewById(R.id.ed6);
        ed7 = (EditText) findViewById(R.id.ed7);
        ed8 = (EditText) findViewById(R.id.ed8);
        ed9 = (EditText) findViewById(R.id.ed9);
        ed11 = (EditText) findViewById(R.id.ed11);
        ed12 = (EditText) findViewById(R.id.ed12);
        ed13 = (EditText) findViewById(R.id.ed13);
        ed14 = (EditText) findViewById(R.id.ed14);
        ed15 = (EditText) findViewById(R.id.ed15);
        ed16 = (EditText) findViewById(R.id.ed16);
        ed17 = (EditText) findViewById(R.id.ed17);
        ed18 = (EditText) findViewById(R.id.ed18);
        ed19 = (EditText) findViewById(R.id.ed19);
        ed10 = (EditText) findViewById(R.id.ed10);
        ed20 = (EditText) findViewById(R.id.ed20);

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
        str11 = Float.parseFloat(ed11.getText().toString());
        str12 = Float.parseFloat(ed12.getText().toString());
        str13 = Float.parseFloat(ed13.getText().toString());
        str14 = Float.parseFloat(ed14.getText().toString());
        str15 = Float.parseFloat(ed15.getText().toString());
        str16 = Float.parseFloat(ed16.getText().toString());
        str17 = Float.parseFloat(ed17.getText().toString());
        str18 = Float.parseFloat(ed18.getText().toString());
        str19 = Float.parseFloat(ed19.getText().toString());
        str10 = Float.parseFloat(ed10.getText().toString());
        str20 = Float.parseFloat(ed20.getText().toString());
        mMarArray[0] = str1;
        mMarArray[1] = str2;
        mMarArray[2] = str3;
        mMarArray[3] = str4;
        mMarArray[4] = str5;
        mMarArray[5] = str6;
        mMarArray[6] = str7;
        mMarArray[7] = str8;
        mMarArray[8] = str9;
        mMarArray[9] = str10;
        mMarArray[10] = str11;
        mMarArray[11] = str12;
        mMarArray[12] = str13;
        mMarArray[13] = str14;
        mMarArray[14] = str15;
        mMarArray[15] = str16;
        mMarArray[16] = str17;
        mMarArray[17] = str18;
        mMarArray[18] = str19;
        mMarArray[19] = str20;

        setImageMatrix();
    }
    private void setImageMatrix(){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mMarArray);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        iv.setImageBitmap(bmp);
    }

    public void restore(View view){
        mMarArray[0] = 1;
        mMarArray[1] = 0;
        mMarArray[2] = 0;
        mMarArray[3] = 0;
        mMarArray[4] = 0;
        mMarArray[5] = 0;
        mMarArray[6] = 1;
        mMarArray[7] = 0;
        mMarArray[8] = 0;
        mMarArray[9] = 0;
        mMarArray[10] = 0;
        mMarArray[11] = 0;
        mMarArray[12] = 1;
        mMarArray[13] = 0;
        mMarArray[14] = 0;
        mMarArray[15] = 0;
        mMarArray[16] = 0;
        mMarArray[17] = 0;
        mMarArray[18] = 1;
        mMarArray[19] = 0;
        ed1.setText((int)mMarArray[0]+"");
        ed2.setText((int)mMarArray[1]+"");
        ed3.setText((int)mMarArray[2]+"");
        ed4.setText((int)mMarArray[3]+"");
        ed5.setText((int)mMarArray[4]+"");
        ed6.setText((int)mMarArray[5]+"");
        ed7.setText((int)mMarArray[6]+"");
        ed8.setText((int)mMarArray[7]+"");
        ed9.setText((int)mMarArray[8]+"");
        ed10.setText((int)mMarArray[9]+"");
        ed11.setText((int)mMarArray[10]+"");
        ed12.setText((int)mMarArray[11]+"");
        ed13.setText((int)mMarArray[12]+"");
        ed14.setText((int)mMarArray[13]+"");
        ed15.setText((int)mMarArray[14]+"");
        ed16.setText((int)mMarArray[15]+"");
        ed17.setText((int)mMarArray[16]+"");
        ed18.setText((int)mMarArray[17]+"");
        ed19.setText((int)mMarArray[18]+"");
        ed20.setText((int)mMarArray[19]+"");
        iv.setImageBitmap(bitmap);
    }
}
