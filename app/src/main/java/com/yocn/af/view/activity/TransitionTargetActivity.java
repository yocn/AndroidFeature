package com.yocn.af.view.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.yocn.af.R;

public class TransitionTargetActivity extends BaseActivity {

    private ImageView coverImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_tar);
        coverImg = findViewById(R.id.iv_cover_tar);
//        postponeEnterTransition();
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
