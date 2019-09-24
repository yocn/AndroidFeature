package com.yocn.af.view.splash;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.yocn.af.R;
import com.yocn.af.view.base.BaseActivity;

/**
 * @Author yocn
 * @Date 2019/9/24 2:55 PM
 * @ClassName MySplashActivity
 */
public class MySplashActivity extends BaseActivity {

    String color = "https://i.loli.net/2019/09/24/t1GcrQ4O6TK7efw.jpg";
    String gray = "https://i.loli.net/2019/09/24/Ny1uEHhmYrKPJoG.jpg";

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView mImageIV = findViewById(R.id.iv_image);
        context = this;
        Glide.with(context)
                .load(gray)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageIV);
        Glide.with(getApplicationContext())
                .load(color)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .animate(animationObject)
                .into(mImageIV);
    }

    ViewPropertyAnimation.Animator animationObject = new ViewPropertyAnimation.Animator() {
        @Override
        public void animate(View view) {
            // if it's a custom view class, cast it here
            // then find subviews and do the animations
            // here, we just use the entire view for the fade animation
            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeAnim.setDuration(2500);
            fadeAnim.start();
        }
    };
}
