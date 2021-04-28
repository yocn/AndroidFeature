package com.yocn.af.view.widget.coord;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.yocn.af.R;
import com.yocn.af.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class CoordTouchViewGroup extends CoordinatorLayout {

    private ImageView iv;

    public CoordTouchViewGroup(@NonNull Context context) {
        super(context);
        init();
    }

    public CoordTouchViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoordTouchViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        iv = findViewById(R.id.iv_2);
        LogUtil.d("yocn" + "  iv:::" + iv);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        init();
    }

    private float deltaX, deltaY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        deltaX = ev.getX() - iv.getLeft();
        deltaY = ev.getY() - iv.getTop();
        LogUtil.d("yocn" + "  iv:::" + iv + " iv[" + iv.getLeft() + "," + iv.getTop() + "," + iv.getRight() + "," + iv.getBottom() + "] " +
                " " + ev.getX() + "," + ev.getY() + " delta:[" + deltaY + "," + deltaY + "]");
        return true;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();
            setPosition(iv, x - deltaX, y - deltaY);
        }
        return true;
    }

    private void setPosition(View v, float x, float y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) v.getLayoutParams();
        layoutParams.leftMargin = (int) x;
        layoutParams.topMargin = (int) y;
        v.setLayoutParams(layoutParams);
    }
}
