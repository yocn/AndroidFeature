package com.yocn.af.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yocn.af.util.LogUtil;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.widget.ViewDragHelper;

public class VDHLayout extends CoordinatorLayout {
    private ViewDragHelper mDragger;

    public VDHLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                LogUtil.d("tryCaptureView:" + child);
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                LogUtil.d("child:" + child + "  left:::" + left);
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }
}