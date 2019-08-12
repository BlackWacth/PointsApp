package com.bruce.points.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import com.bruce.points.ui.screensaver.ScreensaverActivity;

public class ScreenOffReceiver extends BroadcastReceiver {

    @SuppressLint("WakelockTimeout")
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") @SuppressWarnings("deprecation")
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.FULL_WAKE_LOCK, "bright");
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

            Intent screensaverIntent = new Intent(context, ScreensaverActivity.class);
            screensaverIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(screensaverIntent);

            //判断是否是休眠状态
            if (wl.isHeld()) {
                //释放屏幕， 是屏幕休眠
                wl.release();
            }
            if (!wl.isHeld()) {
                //获取屏幕，唤醒屏幕
                wl.acquire();
                wl.release(); //关键代码，如果不要此代码，则会出现一直亮屏，不熄屏的情况
            }
        }
    }
}
