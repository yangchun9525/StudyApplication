package com.example.administrator.on_off_button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by yangchun on 2016-12-6.
 */

public class TestScrollerView extends ScrollView {
    public TestScrollerView(Context context) {
        super(context);
    }

    public TestScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:

                int scrollY = getScrollY();
                if (scrollY == 0) {
                    //允许父View进行事件拦截
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    //禁止父View进行事件拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.onTouchEvent(ev);

    }
}
