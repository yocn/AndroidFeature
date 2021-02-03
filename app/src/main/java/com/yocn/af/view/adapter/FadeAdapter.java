package com.yocn.af.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.Transition;
import com.yocn.af.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * @Author yocn
 * @Date 2019/8/4 10:24 AM
 * @ClassName MainAdapter
 */
public class FadeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    String color0 = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=645909421,2416603470&fm=11&gp=0.jpg";
    String color1 = "https://i.loli.net/2019/09/24/t1GcrQ4O6TK7efw.jpg";
    String color2 = "https://i.loli.net/2019/09/24/lyJ9AziRIE264ux.jpg";
    String color3 = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3463768342,1453739697&fm=26&gp=0.jpg";
    String color4 = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2699437196,2241898528&fm=26&gp=0.jpg";
    String color5 = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2924230206,887931326&fm=26&gp=0.jpg";
    String color6 = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2748278377,925802359&fm=26&gp=0.jpg";
    String color7 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=31006009,1889005559&fm=26&gp=0.jpg";
    String color8 = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1389979529,546157614&fm=26&gp=0.jpg";
    String color9 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=136615058,611164102&fm=26&gp=0.jpg";
    public List<String> mData = new ArrayList<>();

    {
        mData.add(color0);
        mData.add(color1);
        mData.add(color2);
        mData.add(color3);
        mData.add(color4);
        mData.add(color5);
        mData.add(color6);
        mData.add(color7);
        mData.add(color8);
        mData.add(color9);
    }

    private Context mContext;
    private final DrawableCrossFadeFactory factory;

    public static class VH extends RecyclerView.ViewHolder {
        public final ImageView rawIv;
        public final ImageView coverIv;

        public VH(View v) {
            super(v);
            rawIv = v.findViewById(R.id.iv_raw);
            coverIv = v.findViewById(R.id.iv_cover);
        }
    }

    public FadeAdapter(Context mContext) {
        this.mContext = mContext;
        factory = new DrawableCrossFadeFactory.Builder(2000).setCrossFadeEnabled(true).build();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewholder, final int position) {
        VH holder = (VH) viewholder;
        String url = mData.get(position);
        Glide.with(mContext)
                .load(mData.get(position))
                .centerCrop()
                .transform(new GrayscaleTransformation())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.rawIv.setImageDrawable(resource);
                        Glide.with(mContext)
                                .load(mData.get(position))
                                .centerCrop()
                                .transition(withCrossFade(factory))
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                                .into(holder.coverIv);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return mData.size() * 10;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        holder = new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fade, parent, false));
        return holder;
    }
}
