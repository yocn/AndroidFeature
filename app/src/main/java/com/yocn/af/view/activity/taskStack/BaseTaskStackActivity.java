package com.yocn.af.view.activity.taskStack;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yocn.af.R;
import com.yocn.af.presenter.LogUtil;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseTaskStackActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String INDEX = "INDEX";
    private int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_stack);
        applyIntent();
        TextView showTv = findViewById(R.id.tv_show);
        showTv.setText(getClass().getSimpleName());
        findViewById(R.id.fl_root).setOnClickListener(this);
        getActivityStack();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void applyIntent() {
        index = getIntent().getIntExtra(INDEX, 0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fl_root) {
            clickRoot();
        }
    }

    protected void startNext(Class<? extends AppCompatActivity> clazz) {
        Intent intent = new Intent(this, index == 0 ? this.getClass() : clazz);
        intent.putExtra(INDEX, ++index == 2 ? 0 : index);
        startActivity(intent);
    }

    private void getActivityStack() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> appTasks = activityManager.getAppTasks();
        for (int i = 0; i < appTasks.size(); i++) {
            LogUtil.d(i + getSimpleName(appTasks.get(i).getTaskInfo()));
        }
    }

    private String getSimpleName(ActivityManager.RecentTaskInfo recentTaskInfo) {
        return "-" + recentTaskInfo.numActivities + ":" + recentTaskInfo.topActivity;
    }

    protected void clickRoot() {

    }
}
