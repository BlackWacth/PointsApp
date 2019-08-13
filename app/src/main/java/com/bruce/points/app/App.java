package com.bruce.points.app;

import android.app.Application;
import android.content.Intent;

import com.blankj.utilcode.util.ServiceUtils;
import com.blankj.utilcode.util.Utils;
import com.bruce.points.service.ScreenOffService;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        RestartExceptionHandler.getInstance().initRestartExceptionHandler(this);

        if (!ServiceUtils.isServiceRunning(ScreenOffService.class)) {
            Intent intent = new Intent(this, ScreenOffService.class);
            intent.setAction(Intent.ACTION_SCREEN_OFF);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startService(intent);
        }
    }
}
