package com.yocn.af.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.yocn.af.R;
import com.yocn.af.util.LogUtil;
import com.yocn.af.util.TestUtil;

import java.lang.reflect.InvocationTargetException;

import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.PopupWindowCompat;

public class TestWindowActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_window);
        Button mButtom = findViewById(R.id.test);
        mButtom.setOnClickListener(v -> {
            showDialog();
            TestPopupWindow mWindow = new TestPopupWindow(this);
            PopupWindowCompat.showAsDropDown(mWindow, mButtom, 0, 0, Gravity.START);
            try {
                TestUtil.testWindowManagerGlobal();
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                LogUtil.d(e.getMessage());
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 两个按钮的 dialog
     */
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("最普通dialog")
                .setMessage("我是最简单的dialog").setPositiveButton("YES", (dialogInterface, i) -> {
                }).setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.create().show();
    }

    public static class TestPopupWindow extends PopupWindow {

        public TestPopupWindow(Context context) {
            super(context);
            setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            setOutsideTouchable(true);
            setFocusable(true);
            setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            View contentView = LayoutInflater.from(context).inflate(R.layout.popup_test, null, false);
            setContentView(contentView);
        }
    }
}
