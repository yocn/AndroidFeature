package com.yocn.af.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.yocn.af.R;

import androidx.core.app.ActivityOptionsCompat;

public class TransitionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        ImageView clickIv = findViewById(R.id.iv_cover);
        clickIv.setOnClickListener(v -> {
            Toast.makeText(TransitionActivity.this, "aaaaaaaa", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(TransitionActivity.this, TransitionTargetActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(TransitionActivity.this, clickIv, "coverImg");
            startActivity(intent, options.toBundle());
        });
    }
}
