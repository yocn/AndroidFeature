package com.yocn.af;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MApplication extends Application {

    private static MApplication mApplication;
    private Map<Integer, List<Activity>> map = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        registerCallback();
    }

    public static MApplication getApp() {
        return mApplication;
    }

    public void registerCallback() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                int taskId = activity.getTaskId();
                List<Activity> activityList = map.get(taskId);
                if (activityList == null) {
                    activityList = new LinkedList<>();
                }
                activityList.remove(activity);
                activityList.add(activity);
                map.put(taskId, activityList);
                for (Map.Entry<Integer, List<Activity>> task : map.entrySet()) {
                    for (Activity activity1 : task.getValue()) {
//                        LogUtil.d(rtask.getKey() + " / " + activity1.getClass().getName());
                    }
                }
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
//                int taskId = activity.getTaskId();
//                List<Activity> activityList = map.get(taskId);
//                if (activityList == null) {
//                    activityList = new LinkedList<>();
//                }
            }
        });
    }
}
