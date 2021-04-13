package com.yocn.af.view.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yocn.af.R;
import com.yocn.af.util.LogUtil;
import com.yocn.af.util.ViewUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WeChatVoiceView extends FrameLayout {
    private String TAG = "WeChatVoiceView";
    private TextView voice;
    private WeChatVoiceBottomArc weChatVoiceBottomArc;
    private final int ANIM_DURATION = 300;
    private ImageView voiceIv;
    private int bottomArcTransY;

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
        voiceIv = findViewById(R.id.iv_voice);
        weChatVoiceBottomArc = findViewById(R.id.bottom_arc);
        bottomArcTransY = getResources().getDimensionPixelOffset(R.dimen.arc_height);
    }

    private void startAnim() {
        LogUtil.d("weChatVoiceBottomArc.getY()::" + weChatVoiceBottomArc.getY() + " getTranslationY:: " + weChatVoiceBottomArc.getTranslationY());
        ObjectAnimator bottomArcTransYAnim = ObjectAnimator.ofFloat(weChatVoiceBottomArc, "translationY",
                bottomArcTransY, 0);
        ObjectAnimator bottomArcAlphaAnim = ObjectAnimator.ofFloat(weChatVoiceBottomArc, "alpha", 0f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(bottomArcTransYAnim, bottomArcAlphaAnim);
        set.setDuration(ANIM_DURATION);
        set.start();
    }

    public void doStart() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                startAnim();
            }
        });
    }

    public void doDefault() {
        weChatVoiceBottomArc.setTranslationY(bottomArcTransY);
    }

//    private void doAnim() {
//        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(coverImg, "translationX", srcX, tarX);
//        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(coverImg, "translationY", srcY, tarY);
//        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(coverImg, "scaleX", 1f, 1.5f);
//        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(coverImg, "scaleY", 1f, 1.5f);
//        AnimatorSet set = new AnimatorSet();
//        set.playTogether(transXAnim, transYAnim, scaleXAnim, scaleYAnim);
//        set.setDuration(500);
//        set.start();
//    }

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
