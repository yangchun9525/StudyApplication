package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.on_off_button.R;
import com.example.administrator.on_off_button.handler.Handler_01_Activity;
import com.example.administrator.on_off_button.handler.Handler_02_Activity;
import com.example.administrator.on_off_button.handler.Handler_03_Activity;
import com.example.administrator.on_off_button.handler.Handler_04_Activity;
import com.example.administrator.on_off_button.handler.Handler_05_Activity;

/**
 * Created by Administrator on 2016/2/27.
 */
public class MoreActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
    }
    public void toFiveCircle(View view){
        Intent intent = new Intent(MoreActivity.this, FiveCircleActivity.class);
        startActivity(intent);
    }
    public void toImageMatrix(View view){
        startActivity(new Intent(MoreActivity.this,ImageMatrixActivity.class));
    }
    public void toImageEffect(View view){
        startActivity(new Intent(MoreActivity.this,ImageEffectActivity.class));
    }
    public void toImageColorMatrix(View view){
        startActivity(new Intent(MoreActivity.this,ImageColorMatrixActivity.class));
    }
    public void toImagePixerMatrix(View view){
        startActivity(new Intent(MoreActivity.this,ImagePixerActivity.class));
    }
    public void toSurfaceView(View view){
        startActivity(new Intent(MoreActivity.this,SurfaceViewTemplateActivity.class));
    }
    public void toRecycleActivity(View view){
        startActivity(new Intent(MoreActivity.this,RecycleActivity.class));
    }
    public void toHandler01Activity(View view){
        startActivity(new Intent(MoreActivity.this,Handler_01_Activity.class));
    }
    public void toHandler02Activity(View view){
        startActivity(new Intent(MoreActivity.this,Handler_02_Activity.class));
    }
    public void toHandler03Activity(View view){
        startActivity(new Intent(MoreActivity.this,Handler_03_Activity.class));
    }
    public void toHandler04Activity(View view){
        startActivity(new Intent(MoreActivity.this,Handler_04_Activity.class));
    }
    public void toHandler05Activity(View view){
        startActivity(new Intent(MoreActivity.this,Handler_05_Activity.class));
    }
    public void toViewAnimationActivity(View view){
        startActivity(new Intent(MoreActivity.this,ValueAnmiationActivity.class));
    }
    public void toMukeImageViewActivity(View view){
        startActivity(new Intent(MoreActivity.this,MukeImageViewActivity.class));
    }
    public void toTestScrollActivity(View view){
        startActivity(new Intent(MoreActivity.this,TestScrollActivity.class));
    }
    public void toLayoutAnimaActivity(View view){
        startActivity(new Intent(MoreActivity.this,LayoutAnimaActivity.class));
    }
    public void toPendingTransitionFirstActivity(View view){
        startActivity(new Intent(MoreActivity.this,PendingTransitionFirstActivity.class));
    }
    public void toTestCoordinatorActivity(View view){
        startActivity(new Intent(MoreActivity.this,TestCoordinatorActivity.class));
    }
    public void toNotifyActivity(View view){
        startActivity(new Intent(MoreActivity.this,NotifyActivity.class));
    }
    public void toVectorAnimationActivity(View view){
        startActivity(new Intent(MoreActivity.this,VectorAnimationActivity.class));
    }

    public void toTestViewDragViewGroupActivity(View view){
        startActivity(new Intent(MoreActivity.this,TestViewDragViewGroupActivity.class));
    }

    public void toAndroidCallJs(View view){
        startActivity(new Intent(MoreActivity.this,AndroidCallJsActivity.class));
    }
}
