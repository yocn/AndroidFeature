package com.yocn.af.presenter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.yocn.af.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class BtnBehavior extends CoordinatorLayout.Behavior<Button> {
    private int width;

    public BtnBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        width = display.widthPixels;
    }

    /**
     * 判断child的布局是否依赖dependency
     */
    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull Button button, @NonNull View dependency) {
        //根据逻辑判断rs的取值
        //返回false表示child不依赖dependency，ture表示依赖
//        LogUtil.d("layoutDependsOn parent:" + parent.getClass().getSimpleName() + " child:" + button.getClass().getSimpleName() + " dependency:" + dependency.getClass().getSimpleName());
        return true;
    }

    /**
     * 当dependency发生改变时（位置、宽高等），执行这个函数
     * 返回true表示child的位置或者是宽高要发生改变，否则就返回false
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull Button button, @NonNull View dependency) {
        //child要执行的具体动作
        int top = dependency.getTop();
        int left = dependency.getLeft();

        int x = width - left - button.getWidth();
        int y = top;
//        LogUtil.d("onDependentViewChanged x:" + x + " y:" + y + "   width：" + width);

        setPosition(button, x, y);
        return true;
    }

    private void setPosition(View v, int x, int y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) v.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        v.setLayoutParams(layoutParams);
    }
}
