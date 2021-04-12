package com.yocn.af.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.yocn.af.presenter.LogUtil;
import com.yocn.af.presenter.ViewUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WeChatVoiceView extends FrameLayout {
    private String TAG = "WeChatVoiceView";

    public WeChatVoiceView(@NonNull Context context) {
        super(context);
    }

    public WeChatVoiceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeChatVoiceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        LogUtil.d(TAG + "::dispatchTouchEvent::" + result + "  " + ViewUtil.printEvent(ev));
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        LogUtil.d(TAG + "::onInterceptTouchEvent::" + result + "  " + ViewUtil.printEvent(ev));
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        LogUtil.d(TAG + "::onTouchEvent::" + result + "    ");
        return result;
    }

}
