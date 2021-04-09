package com.yocn.af.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yocn.af.R;
import com.yocn.af.presenter.LogUtil;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TransitionActivity extends BaseActivity {
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rv);
        Adapter adapter = new Adapter();
        adapter.setmContext(this);
        adapter.setOnClickListener(v -> {
            Intent intent = new Intent(TransitionActivity.this, TransitionTargetActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(TransitionActivity.this, v, "coverImg");
            int[] location = new int[2];
            v.getLocationOnScreen(location);
            int x = location[0]; // view距离 屏幕左边的距离（即x轴方向）
            int y = location[1]; // view距离 屏幕顶边的距离（即y轴方向）
            int width = v.getWidth();
            int height = v.getHeight();
            LogUtil.d("x" + x + " y:" + y + " w:" + width + " h:" + height);
            intent.putExtra("x", x);
            intent.putExtra("y", y);
            intent.putExtra("w", width);
            intent.putExtra("h", height);
            startActivity(intent, options.toBundle());

        });
        mRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public static class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context mContext;
        private View.OnClickListener onClickListener;

        private int[] colors = {R.color.color1, R.color.color2, R.color.color3
                , R.color.color4, R.color.color5, R.color.color6
                , R.color.color7, R.color.color8, R.color.color9};

        private int[] textColor = {R.color.write, R.color.black, R.color.write
                , R.color.black, R.color.black, R.color.black
                , R.color.write, R.color.black, R.color.write};

        public void setmContext(Context mContext) {
            this.mContext = mContext;
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        public static class VH extends RecyclerView.ViewHolder {
            public final LinearLayout all;
            public final TextView title;
            public final ImageView iv_cover;

            public VH(View v) {
                super(v);
                all = v.findViewById(R.id.all);
                title = v.findViewById(R.id.title);
                iv_cover = v.findViewById(R.id.iv_cover);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jump, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewholder, int position) {
            VH holder = (VH) viewholder;
            holder.title.setTextColor(mContext.getResources().getColor(textColor[position % textColor.length]));
            holder.all.setBackgroundResource(colors[position % colors.length]);
            holder.itemView.setOnClickListener(v -> {
                if (onClickListener != null) {
                    onClickListener.onClick(holder.iv_cover);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

}
