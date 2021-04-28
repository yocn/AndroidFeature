package com.yocn.af.view.widget.coord;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yocn.af.R;
import com.yocn.af.util.LogUtil;
import com.yocn.af.util.ViewUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class CoordTouchViewGroup extends LinearLayout {
    private ImageView iv;
    private float deltaX, deltaY;
    private boolean start;

    public CoordTouchViewGroup(@NonNull Context context) {
        super(context);
    }

    public CoordTouchViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CoordTouchViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        iv = findViewById(R.id.iv_2);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        init();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.d("onInterceptTouachE1vent:::" + start);
        if (start) {
            deltaX = ev.getX() - iv.getLeft();
            deltaY = ev.getY() - iv.getTop();
            return true;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.d("dispatch1aKeyEvent:::" + start + "  super.dispatchTouchEvent(ev):" + super.dispatchTouchEvent(ev));
        if (!start && super.dispatchTouchEvent(ev)) {
            start = true;
        }
        return start;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("CoordTouchViewGroup1  .getAction()：" + ViewUtil.printEvent(event) + "  super:" + super.onTouchEvent(event));
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();
            setPosition(iv, x - deltaX, y - deltaY);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            start = false;
        }
        return true;
    }

    public static boolean pointInRect(float x, float y, Rect rect) {
        return x > rect.left && x < rect.right && y > rect.top && y < rect.bottom;
    }

    private void setPosition(View v, float x, float y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) v.getLayoutParams();
        layoutParams.leftMargin = (int) x;
        layoutParams.topMargin = (int) y;
        v.setLayoutParams(layoutParams);
    }
}
