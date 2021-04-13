package com.yocn.af.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yocn.af.R;
import com.yocn.af.util.LogUtil;
import com.yocn.af.util.ViewUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WeChatVoiceView extends FrameLayout {
    private String TAG = "WeChatVoiceView";
    private TextView voice;

    public WeChatVoiceView(@NonNull Context context) {
        this(context, null);
    }

    public WeChatVoiceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeChatVoiceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_voice, this);
        voice = findViewById(R.id.voice);
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
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean result = super.onTouchEvent(ev);
        LogUtil.d(TAG + "::onTouchEvent::" + result + "    ");
        float x = ev.getX();
        float y = ev.getY();
        voice.setText(ViewUtil.printEvent(ev) + "[" + x + "/" + y + "]");
        return true;
    }

}
