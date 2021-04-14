package com.yocn.af.view.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yocn.af.R;
import com.yocn.af.util.DisplayUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WeChatVoiceView extends FrameLayout {
    private String TAG = "WeChatVoiceView";
    private TextView voice;
    private WeChatVoiceBottomArc weChatVoiceBottomArcLight;
    private WeChatVoiceBottomArc weChatVoiceBottomArcDark;
    private final int ANIM_DURATION = 300;
    private final int ANIM_DURATION_TEXT = 500;
    private ImageView voiceIv;
    private int bottomArcTransY;
    private TextView voiceTv;
    private TextView cancelTv;
    private TextView translateTv;
    private ObjectAnimator darkAlphaAnim;
    private ObjectAnimator lightAlphaAnim;
    boolean currentArcLight = true;
    boolean lightAniming = false;
    boolean darkAniming = false;
    private AnimatorSet bottomArcSet;
    private AnimatorSet textAnimSet;

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
        initView();
        initAnimation();
    }

    private void initView() {
        voice = findViewById(R.id.voice);
        voiceIv = findViewById(R.id.iv_voice);
        weChatVoiceBottomArcLight = findViewById(R.id.bottom_arc_light);
        weChatVoiceBottomArcDark = findViewById(R.id.bottom_arc_dark);
        cancelTv = findViewById(R.id.tv_cancel);
        translateTv = findViewById(R.id.tv_trans);
        bottomArcTransY = getResources().getDimensionPixelOffset(R.dimen.arc_height_light);
        voiceTv = findViewById(R.id.voice);
    }

    private void initAnimation() {
        ObjectAnimator bottomArcTransYAnim = ObjectAnimator.ofFloat(weChatVoiceBottomArcLight, "translationY", bottomArcTransY, 0);
        ObjectAnimator bottomArcAlphaAnim = ObjectAnimator.ofFloat(weChatVoiceBottomArcLight, "alpha", 0f, 1f);
        ObjectAnimator voiceTvTransYAnim = ObjectAnimator.ofFloat(voiceTv, "translationY", 300, 0);
        ObjectAnimator voiceTvAlphaAnim = ObjectAnimator.ofFloat(voiceTv, "alpha", 0f, 1f);
        weChatVoiceBottomArcLight.setVisibility(View.VISIBLE);
        bottomArcSet = new AnimatorSet();
        bottomArcSet.playTogether(bottomArcTransYAnim, bottomArcAlphaAnim, voiceTvTransYAnim, voiceTvAlphaAnim);
        bottomArcSet.setDuration(ANIM_DURATION);
        bottomArcSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                weChatVoiceBottomArcDark.setVisibility(View.VISIBLE);
            }
        });
        ObjectAnimator cancelTvTransYAnim = ObjectAnimator.ofFloat(cancelTv, "translationY", 100, 0);
        ObjectAnimator cancelTvAlphaAnim = ObjectAnimator.ofFloat(cancelTv, "alpha", 0f, 1f);
        ObjectAnimator translateTvTransYAnim = ObjectAnimator.ofFloat(translateTv, "translationY", 100, 0);
        ObjectAnimator translateTvAlphaAnim = ObjectAnimator.ofFloat(translateTv, "alpha", 0f, 1f);
        textAnimSet = new AnimatorSet();
        textAnimSet.playTogether(cancelTvTransYAnim, cancelTvAlphaAnim, translateTvTransYAnim, translateTvAlphaAnim);
        textAnimSet.setDuration(ANIM_DURATION_TEXT);

        darkAlphaAnim = ObjectAnimator.ofFloat(weChatVoiceBottomArcLight, "alpha", 1f, 0f);
        darkAlphaAnim.setDuration(100);
        darkAlphaAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                darkAniming = false;
                currentArcLight = false;
            }
        });

        lightAlphaAnim = ObjectAnimator.ofFloat(weChatVoiceBottomArcLight, "alpha", 0f, 1f);
        lightAlphaAnim.setDuration(100);
        lightAlphaAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                lightAniming = false;
                currentArcLight = true;
            }
        });
    }


    private void startAnim() {
        bottomArcSet.start();
        textAnimSet.start();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void doStart() {
        new Handler(Looper.getMainLooper()).post(this::startAnim);
    }

    public void doDefault() {
        weChatVoiceBottomArcLight.setTranslationY(bottomArcTransY);
        weChatVoiceBottomArcDark.setVisibility(View.GONE);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        if (weChatVoiceBottomArcLight.isOnRect(x, y)) {
            tryChangeToLight();
        } else {
            tryChangeToDark();
        }
        return true;
    }

    private void tryChangeToDark() {
        if (!currentArcLight || darkAniming) {
            return;
        }
        darkAniming = true;
        darkAlphaAnim.start();
    }

    private void tryChangeToLight() {
        if (currentArcLight || lightAniming) {
            return;
        }
        lightAniming = true;
        lightAlphaAnim.start();
    }

}
