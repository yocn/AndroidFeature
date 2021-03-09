package com.yocn.af.presenter;

import android.view.MotionEvent;

public class ViewUtil {
    public static String printEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return "null";
        }
        String result = "";
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                result = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_UP:
                result = "ACTION_UP";
                break;
            case MotionEvent.ACTION_MOVE:
                result = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_CANCEL:
                result = "ACTION_CANCEL";
                break;
            default:
        }
        return result;
    }
}
