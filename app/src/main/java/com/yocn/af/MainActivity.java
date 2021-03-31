package com.yocn.af;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.yocn.af.module.base.JumpBean;
import com.yocn.af.presenter.DisplayUtil;
import com.yocn.af.util.TestUtil;
import com.yocn.af.view.activity.BaseActivity;
import com.yocn.af.view.adapter.MainAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * @author yocn
 */
public class MainActivity extends BaseActivity {
    RecyclerView mRecyclerView;
    RelativeLayout mTopRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_main, null);
        setContentView(rootView);
        initView(rootView);
        initData();
    }

    private void initView(View root) {
        mRecyclerView = root.findViewById(R.id.rv_main);
        mTopRL = root.findViewById(R.id.rl_top);
        TestUtil.testSingleTon();
    }

    private int currentY;

    private void initData() {
        List<JumpBean> data = MainAdapter.getDataList();
        MainAdapter mMainAdapter = new MainAdapter(data);
        mMainAdapter.setmContext(this);
        int spanCount;
        if (data.size() < 6) {
            spanCount = 2;
        } else if (data.size() < 24) {
            spanCount = 3;
        } else {
            spanCount = 4;
        }
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                                @Override
                                                public int getSpanSize(int position) {
                                                    return position == 0 ? gridLayoutManager.getSpanCount() : 1;
                                                }
                                            }
        );

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mMainAdapter);

        final int min = DisplayUtil.dip2px(this, 76);
        final int max = DisplayUtil.dip2px(this, 140);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentY += dy;
                if (currentY < min) {
                    mTopRL.setVisibility(View.GONE);
                    DisplayUtil.setAndroidNativeLightStatusBar(MainActivity.this, false);
                } else {
                    mTopRL.setVisibility(View.VISIBLE);
                    DisplayUtil.setAndroidNativeLightStatusBar(MainActivity.this, true);
                    if (currentY < max) {
                        int percent = (currentY - min) * 100 / (max - min);
                        String color = DisplayUtil.getColor(percent);
                        mTopRL.setBackgroundColor(Color.parseColor(color));
                    } else {
                        mTopRL.setBackgroundResource(R.color.gray);
                    }

                }
            }
        });
    }

}
