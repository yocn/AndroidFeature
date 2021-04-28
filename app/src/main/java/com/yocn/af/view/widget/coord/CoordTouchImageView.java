package com.yocn.af.view.widget.coord;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.yocn.af.util.LogUtil;
import com.yocn.af.util.ViewUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class CoordTouchImageView extends AppCompatImageView {
    public CoordTouchImageView(@NonNull Context context) {
        super(context);
    }

    public CoordTouchImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CoordTouchImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.d("dispatchTouchEvent:::::::::" + ViewUtil.printEvent(ev));
        return ev.getAction() == MotionEvent.ACTION_DOWN;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}