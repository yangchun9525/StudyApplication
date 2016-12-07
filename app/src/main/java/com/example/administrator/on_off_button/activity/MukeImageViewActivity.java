package com.example.administrator.on_off_button.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.on_off_button.R;
import com.example.administrator.on_off_button.ZoomImageView;

/**
 * Created by Administrator on 2016/3/10.
 */
public class MukeImageViewActivity extends Activity{
    private ViewPager viewPager;
    private int[] mImgs = new int[]{R.drawable.test,R.drawable.test2,R.drawable.test3};
    private ImageView[] mImageViews = new ImageView[mImgs.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muke);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ZoomImageView imageView = new ZoomImageView(getApplicationContext());
                imageView.setImageResource(mImgs[position]);
                container.addView(imageView);
                mImageViews[position] = imageView;
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImageViews[position]);
            }

            @Override
            public int getCount() {
                return mImageViews.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }
}
