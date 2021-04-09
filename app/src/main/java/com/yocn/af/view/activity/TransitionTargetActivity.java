package com.yocn.af.view.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yocn.af.R;
import com.yocn.af.presenter.DisplayUtil;

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
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) coverImg.getLayoutParams();
        layoutParams.topMargin = srcX;
        layoutParams.leftMargin = srcY;
        coverImg.setLayoutParams(layoutParams);
        int[] wh = DisplayUtil.getHW(this);
        tarX = wh[0] / 2 - w / 2;
        tarY = 200;
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                doAnim();
            }
        });
    }

    private void doAnim() {
        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(coverImg, "translationX", srcX, tarX);
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(coverImg, "translationY", srcY, tarY);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(transXAnim, transYAnim);
        set.setDuration(200);
        set.start();
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
