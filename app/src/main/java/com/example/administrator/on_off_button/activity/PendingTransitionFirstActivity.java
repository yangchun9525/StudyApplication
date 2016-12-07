package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.on_off_button.R;

/**
 * Created by yangchun on 2016-12-7.
 */
public class PendingTransitionFirstActivity extends Activity implements View.OnClickListener {
    private TextView tv;
    private ImageView iv;
    private View iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_transition_first);
        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);
        iv1 =  findViewById(R.id.iv1);
        tv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(PendingTransitionFirstActivity.this,PendingTransitionSecondActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //无共享元素
//                    MainActivity.this.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            //一个共享元素
//                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, iv,"ivtest").toBundle());
            //多个共享元素
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                    this, Pair.create(view, "ivtest"),Pair.create(iv1, "ivtest1")).toBundle());
        }
    }
}
