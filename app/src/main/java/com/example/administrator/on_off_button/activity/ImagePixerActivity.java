package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.on_off_button.R;
import com.example.administrator.on_off_button.Util.ImageHelper;

/**
 * Created by Administrator on 2016/2/27.
 */
public class ImagePixerActivity extends Activity{
    private ImageView iv1,iv2,iv3,iv4;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pixer);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test2);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv1.setImageBitmap(bitmap);
        iv2.setImageBitmap(ImageHelper.handleImageNegative(bitmap));
        iv3.setImageBitmap(ImageHelper.handleImagePixelsOldPhoto(bitmap));
        iv4.setImageBitmap(ImageHelper.handleImagePixelsRelief(bitmap));
    }
}
