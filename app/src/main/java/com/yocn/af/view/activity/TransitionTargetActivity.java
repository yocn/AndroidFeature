package com.yocn.af.view.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.yocn.af.R;
import com.yocn.af.util.DisplayUtil;

public class TransitionTargetActivity extends BaseActivity {

    private ImageView coverImg;
    private int tarX, tarY, srcX, srcY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_tar);
        coverImg = findViewById(R.id.iv_cover_tar);
        srcX = getIntent().getIntExtra("x", 0);
        srcY = getIntent().getIntExtra("y", 0);
        int w = getIntent().getIntExtra("w", 0);
        int h = getIntent().getIntExtra("h", 0);
        coverImg.setX(srcX);
        coverImg.setY(srcY);
        int[] wh = DisplayUtil.getHW(this);
        tarX = wh[0] / 2 - w / 2;
        tarY = 200;
        new Handler().postDelayed(this::doAnim, 150);
    }

    private void doAnim() {
        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(coverImg, "translationX", srcX, tarX);
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(coverImg, "translationY", srcY, tarY);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(coverImg, "scaleX", 1f, 1.5f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(coverImg, "scaleY", 1f, 1.5f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(transXAnim, transYAnim, scaleXAnim, scaleYAnim);
        set.setDuration(500);
        set.start();
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
