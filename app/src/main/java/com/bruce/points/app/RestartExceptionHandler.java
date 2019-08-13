package com.bruce.points.app;

import android.content.Intent;

import com.blankj.utilcode.util.ActivityUtils;

public class RestartExceptionHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "RestartExceptionHandler";

    private static RestartExceptionHandler mReStartExceptionHandler;

    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private App mAppContext;

    public static RestartExceptionHandler getInstance(){
        if (mReStartExceptionHandler == null) {
            mReStartExceptionHandler = new RestartExceptionHandler();
        }
        return mReStartExceptionHandler;
    }

    public void initRestartExceptionHandler(App application){
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mAppContext = application;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(!handleException(ex) && mDefaultHandler != null){
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        }else{
            Intent intent = mAppContext.getPackageManager().getLaunchIntentForPackage(mAppContext.getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mAppContext.startActivity(intent);
            ActivityUtils.finishAllActivities();
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        return true;
    }
}
