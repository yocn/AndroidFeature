package com.yocn.af.view.activity.taskStack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yocn.af.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseTaskStackActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String STACK = "STACK";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_stack);
        TextView showTv = findViewById(R.id.tv_show);
        showTv.setText(getClass().getSimpleName());
        findViewById(R.id.fl_root).setOnClickListener(this);
        getActivityStack();
        startNext();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fl_root) {
            clickRoot();
        }
    }

    protected void startNext() {
        Serializable list = getIntent().getSerializableExtra(STACK);
        ArrayList<StackBean> stackBeans = (ArrayList<StackBean>) list;
        startNext(stackBeans);
    }

    protected void startNext(ArrayList<StackBean> stackBeans) {
        if (stackBeans == null || stackBeans.size() == 0) {
            return;
        }
        StackBean first = stackBeans.get(0);
        stackBeans.remove(0);
        if (first != null) {
            Intent intent = new Intent(this, first.clazz);
            intent.putExtra(STACK, stackBeans);
            startActivity(intent);
        }
    }

    private void getActivityStack() {
//        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        List<ActivityManager.AppTask> appTasks = activityManager.getAppTasks();
//        for (int i = 0; i < appTasks.size(); i++) {
//            LogUtil.d("TaskId:" + getTaskId() + " " + getSimpleName(appTasks.get(i).getTaskInfo()));
//        }
    }

    private String getSimpleName(ActivityManager.RecentTaskInfo recentTaskInfo) {
        String activityName = recentTaskInfo.topActivity.getShortClassName();
        return "-" + recentTaskInfo.numActivities + ":" + activityName.substring(activityName.lastIndexOf("."));
    }

    protected void clickRoot() {

    }

}
