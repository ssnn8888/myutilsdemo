package com.ssnn.mylibrary;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

/**
 * Utils初始化类, getApp() 可获取 ApplicationContext 对象.
 */

public class UtilsInit {

    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    private UtilsInit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        init((Application) context.getApplicationContext());
    }

    /**
     * Init utils.
     * <p>Init it in the class of Application.</p>
     *
     * @param app application
     */
    public static void init(@NonNull final Application app) {
        if (sApplication == null) {
            UtilsInit.sApplication = app;
        }
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Application getApp() {
        if (sApplication != null) {
            return sApplication;
        }

        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object at = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(at);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
            init((Application) app);
            return sApplication;
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new NullPointerException("u should init first");
    }
}
