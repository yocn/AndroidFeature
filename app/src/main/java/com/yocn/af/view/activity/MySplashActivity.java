package com.yocn.af.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.Transition;
import com.yocn.af.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mImageIV1 = findViewById(R.id.iv_image1);
        mImageIVTop1 = findViewById(R.id.iv_image_top1);
        mImageIV2 = findViewById(R.id.iv_image2);
        mImageIVTop2 = findViewById(R.id.iv_image_top2);
        Glide.get(MySplashActivity.this).clearMemory();
        DrawableCrossFadeFactory factory =
                new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        Glide.with(MySplashActivity.this)
                .load(gray1)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        mImageIV1.setImageDrawable(resource);
                        Glide.with(getApplicationContext())
                                .load(color1)
                                .centerCrop()
                                .transition(withCrossFade(factory))
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                                .into(mImageIVTop1);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        Glide.with(MySplashActivity.this)
                .load(gray2)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        mImageIV2.setImageDrawable(resource);
                        Glide.with(getApplicationContext())
                                .load(color2)
                                .centerCrop()
                                .transition(withCrossFade(factory))
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                                .into(mImageIVTop2);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        mImageIV1 = null;
        mImageIV2 = null;
        mImageIVTop1 = null;
        mImageIVTop2 = null;
        super.onDestroy();
    }
}
