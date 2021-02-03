package com.yocn.af.view.activity;

import android.os.Bundle;

import com.yocn.af.R;
import com.yocn.af.view.adapter.FadeAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author yocn
 * @Date 2019/9/24 2:55 PM
 * @ClassName MySplashActivity
 */
public class ColorFadeInActivity2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade2);

        RecyclerView recyclerView = findViewById(R.id.rv_fade);

        FadeAdapter fadeAdapter = new FadeAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(fadeAdapter);
//        DrawableCrossFadeFactory factory =
//                new DrawableCrossFadeFactory.Builder(2000).setCrossFadeEnabled(true).build();
//        Glide.with(ColorFadeInActivity2.this)
//                .load(color1)
//                .centerCrop()
//                .transform(new GrayscaleTransformation())
////                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                .into(new CustomTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        mImageIV1.setImageDrawable(resource);
//                        Glide.with(getApplicationContext())
//                                .load(color1)
//                                .centerCrop()
//                                .transition(withCrossFade(factory))
//                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                                .into(mImageIVTop1);
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                    }
//                });
//
//        Glide.with(ColorFadeInActivity2.this)
//                .load(color2)
//                .centerCrop()
//                .transform(new GrayscaleTransformation())
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                .into(new CustomTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        mImageIV2.setImageDrawable(resource);
//                        Glide.with(getApplicationContext())
//                                .load(color2)
//                                .centerCrop()
//                                .transition(withCrossFade(factory))
//                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                                .into(mImageIVTop2);
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                    }
//                });
    }


}
