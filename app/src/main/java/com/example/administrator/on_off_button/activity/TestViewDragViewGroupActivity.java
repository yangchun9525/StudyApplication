package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.on_off_button.R;
import com.example.administrator.on_off_button.ViewDragViewGroup;

/**
 * Created by Administrator on 2016/1/14.
 */
public class TestViewDragViewGroupActivity extends Activity{
    private RelativeLayout menuView;
    private ViewDragViewGroup mParentView;
    private TextView mTextView;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_drag_view_group);
        mParentView = (ViewDragViewGroup) findViewById(R.id.parentView);
        menuView = (RelativeLayout) findViewById(R.id.menuView);
        mTextView = (TextView) mParentView.getView(R.id.tv, menuView);
        mImageView = (ImageView) mParentView.getView(R.id.iv, menuView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestViewDragViewGroupActivity.this, "click textView", Toast.LENGTH_SHORT).show();
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestViewDragViewGroupActivity.this, "click mImageView", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
