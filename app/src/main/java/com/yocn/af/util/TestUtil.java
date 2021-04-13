package com.yocn.af.util;

import com.yocn.af.module.base.JumpBean;
import com.yocn.af.view.activity.ColorFadeInActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestUtil {
    public final static class TestStaticSingleton {
        private static TestStaticSingleton instance;

        private TestStaticSingleton() {

        }

        public TestStaticSingleton getInstance() {
            if (instance == null) {
                instance = new TestStaticSingleton();
            }
            return instance;
        }
    }

    public static void testWindowManagerGlobal() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class clazz = Class.forName("android.view.WindowManagerGlobal");
        Field mViewsField = clazz.getDeclaredField("mViews");
        mViewsField.setAccessible(true);
        Field mRootsField = clazz.getDeclaredField("mRoots");
        mRootsField.setAccessible(true);
        Field mParamsField = clazz.getDeclaredField("mParams");
        mParamsField.setAccessible(true);
        Method instanceMedthod = clazz.getMethod("getInstance");
        Object mGlobal = instanceMedthod.invoke(null);
        Object mViews = mViewsField.get(mGlobal);
        Object mRoots = mRootsField.get(mGlobal);
        if (mViews instanceof List) {
            for (Object o : (List) mViews) {
                printDecorView(o);
            }
        }
        if (mRoots instanceof List) {
            for (Object o : (List) mRoots) {
                printViewRootImpl(o);
            }
        }
    }

    public static void printViewRootImpl(Object viewRootImpl) throws NoSuchFieldException, IllegalAccessException {
        Field mViewField = viewRootImpl.getClass().getDeclaredField("mView");
        mViewField.setAccessible(true);
        Object mViewObject = mViewField.get(viewRootImpl);
        LogUtil.d("printViewRootImpl->" + viewRootImpl + "  View:" + mViewObject.toString());
    }

    public static void printDecorView(Object decorView) throws IllegalAccessException {
        StringBuffer sb = new StringBuffer(decorView.toString());
        Field mWindowField = null;
        try {
            mWindowField = decorView.getClass().getDeclaredField("mWindow");
            mWindowField.setAccessible(true);
            Object mWindowObject = mWindowField.get(decorView);
            sb.append("   PhoneWindow->").append(mWindowObject.toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            sb.append("   PhoneWindow->").append(decorView.getClass().toString());
        }
        LogUtil.d("printDecorView->:" + sb.toString());
    }

    public static void testSingleTon() {
        Singleton<JumpBean> singleton = new Singleton<JumpBean>() {
            @Override
            protected JumpBean create() {
                return new JumpBean("Color Fade In", ColorFadeInActivity.class);
            }
        };
    }
}
