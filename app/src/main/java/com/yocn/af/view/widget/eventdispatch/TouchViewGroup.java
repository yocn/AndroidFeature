package com.yocn.af.view.widget.eventdispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.yocn.af.util.LogUtil;
import com.yocn.af.util.ViewUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * public boolean dispatchTouchEvent(MotionEvent ev) {
 * boolean consume = false;//事件是否被消费
 * if (onInterceptTouchEvent(ev)){//调用onInterceptTouchEvent判断是否拦截事件
 * consume = onTouchEvent(ev);//如果拦截则调用自身的onTouchEvent方法
 * }else{
 * consume = child.dispatchTouchEvent(ev);//不拦截调用子View的dispatchTouchEvent方法
 * }
 * return consume;//返回值表示事件是否被消费，true事件终止，false调用父View的onTouchEvent方法
 * }
 */

public class TouchViewGroup extends FrameLayout {
    private String position = "";

    public TouchViewGroup(@NonNull Context context) {
        super(context);
    }

    public TouchViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            LogUtil.logWithInterval(position + "::dispatchTouchEvent::" + result + "  " + ViewUtil.printEvent(ev));
        } else {
            LogUtil.d(position + "::dispatchTouchEvent::" + result + "  " + ViewUtil.printEvent(ev));
        }
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        LogUtil.d(position + "::onInterceptTouchEvent::" + result);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        LogUtil.d(position + "::onTouchEvent::" + result);
        return true;
    }


}
