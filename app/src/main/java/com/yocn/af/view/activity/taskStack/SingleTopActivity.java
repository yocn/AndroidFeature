package com.yocn.af.view.activity.taskStack;

import android.os.Bundle;

import androidx.annotation.Nullable;

public class SingleTopActivity extends BaseTaskStackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void clickRoot() {
        startNext(SingleTaskActivity.class);
    }
}
