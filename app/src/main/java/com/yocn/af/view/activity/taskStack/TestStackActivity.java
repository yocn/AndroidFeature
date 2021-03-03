package com.yocn.af.view.activity.taskStack;

import android.os.Bundle;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class TestStackActivity extends BaseTaskStackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void clickRoot() {
        ArrayList<StackBean> stackBeans = new ArrayList<>();
        stackBeans.add(new StackBean(StandardActivity.class));
        stackBeans.add(new StackBean(SingleTaskActivity.class));
        stackBeans.add(new StackBean(StandardActivity.class));
        stackBeans.add(new StackBean(SingleTopActivity.class));
        stackBeans.add(new StackBean(StandardActivity.class));
        stackBeans.add(new StackBean(SingleInstanceActivity.class));
        stackBeans.add(new StackBean(StandardActivity.class));
        startNext(stackBeans);
    }
}
