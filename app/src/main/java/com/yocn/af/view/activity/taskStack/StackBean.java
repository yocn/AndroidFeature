package com.yocn.af.view.activity.taskStack;

import java.io.Serializable;

public class StackBean implements Serializable {
    public Class<? extends BaseTaskStackActivity> clazz;

    public StackBean(Class<? extends BaseTaskStackActivity> clazz) {
        this.clazz = clazz;
    }
}
