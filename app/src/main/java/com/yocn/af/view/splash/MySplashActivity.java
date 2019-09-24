package com.yocn.af.view.splash;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.yocn.af.R;
import com.yocn.af.view.base.BaseActivity;

/**
 * @Author yocn
 * @Date 2019/9/24 2:55 PM
 * @ClassName MySplashActivity
 */
public class MySplashActivity extends BaseActivity {

    String color1 = "https://i.loli.net/2019/09/24/t1GcrQ4O6TK7efw.jpg";
    String gray1 = "https://i.loli.net/2019/09/24/Ny1uEHhmYrKPJoG.jpg";
    //https://i.loli.net/2019/09/24/lyJ9AziRIE264ux.jpg
    //https://i.loli.net/2019/09/24/QdEOUKNeaishADR.jpg
    String color2 = "https://i.loli.net/2019/09/24/lyJ9AziRIE264ux.jpg";
    String gray2 = "https://i.loli.net/2019/09/24/QdEOUKNeaishADR.jpg";
    ImageView mImageIV1;
    ImageView mImageIVTop1;
    ImageView mImageIV2;
    ImageView mImageIVTop2;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mImageIV1 = findViewById(R.id.iv_image1);
        mImageIVTop1 = findViewById(R.id.iv_image_top1);
        mImageIV2 = findViewById(R.id.iv_image2);
        mImageIVTop2 = findViewById(R.id.iv_image_top2);
        context = this;
        Glide.with(context)
                .load(gray1)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(simpleTarget);
        Glide.with(context)
                .load(gray2)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(simpleTarget2);

    }

    SimpleTarget<GlideDrawable> simpleTarget = new SimpleTarget<GlideDrawable>() {
        @Override
        public void onResourceReady(GlideDrawable resource, GlideAnimation glideAnimation) {
            mImageIV1.setImageDrawable(resource);
            Glide.with(getApplicationContext())
                    .load(color1)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .animate(animationObject)
                    .into(mImageIVTop1);
        }
    };

    SimpleTarget<GlideDrawable> simpleTarget2 = new SimpleTarget<GlideDrawable>() {
        @Override
        public void onResourceReady(GlideDrawable resource, GlideAnimation glideAnimation) {
            mImageIV2.setImageDrawable(resource);
            Glide.with(getApplicationContext())
                    .load(color2)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .animate(animationObject)
                    .into(mImageIVTop2);
        }
    };


    ViewPropertyAnimation.Animator animationObject = view -> {
        // if it's a custom view class, cast it here
        // then find subviews and do the animations
        // here, we just use the entire view for the fade animation
        ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        fadeAnim.setDuration(2500);
        fadeAnim.start();
    };

    @Override
    protected void onDestroy() {
        mImageIV1 = null;
        mImageIV2 = null;
        mImageIVTop1 = null;
        mImageIVTop2 = null;
        super.onDestroy();
    }
}
