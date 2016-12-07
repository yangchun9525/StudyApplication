package com.example.administrator.on_off_button;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/3.
 */
public class TopBar extends RelativeLayout{
    private static final int DEFAULT_TEXT_COLOR = Color.BLACK;
    private int mLeftTextColor = DEFAULT_TEXT_COLOR;
    private Drawable mLeftBackground;
    private String mLeftText;

    private int mRightTextColor = DEFAULT_TEXT_COLOR;
    private Drawable mRightBackground;
    private String mRightText;

    private String mTitleText;
    private int mTitleTextColor;
    private float mTitleTextSize;

    private Button mLeftButton,mRightButton;
    private TextView mTitleTextView;

    private LayoutParams mLeftParams,mRightParams,mTitleParams;
    private TopBaiClickInterface mTopBaiClickInterface;


    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, DEFAULT_TEXT_COLOR);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, DEFAULT_TEXT_COLOR);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        mTitleText = ta.getString(R.styleable.TopBar_topBarTitle);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_mainTitleTextColor, DEFAULT_TEXT_COLOR);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);

        //完成资源的回收
        ta.recycle();

        init(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context) {
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleTextView = new Button(context);

        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleTextView.setText(mTitleText);
        mTitleTextView.setTextColor(mTitleTextColor);
        mTitleTextView.setTextSize(mTitleTextSize);
        mTitleTextView.setGravity(CENTER_IN_PARENT);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(mTitleTextView, mTitleParams);

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopBaiClickInterface.rightClick();
            }
        });

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopBaiClickInterface.leftClick();
            }
        });
    }

    public void setOnTopBarClickListener(TopBaiClickInterface mTopBaiClickInterface){
        this.mTopBaiClickInterface = mTopBaiClickInterface;
    }

    public void setButtonVisable(int id,boolean flag){
        if(id == 0){
            if(flag){
                mLeftButton.setVisibility(VISIBLE);
            }else {
                mLeftButton.setVisibility(GONE);
            }
        }else if(id == 1){
            if(flag){
                mRightButton.setVisibility(VISIBLE);
            }else {
                mRightButton.setVisibility(GONE);
            }
        }
    }
    public interface  TopBaiClickInterface{
        void leftClick();
        void rightClick();
    }
}
