package com.bruce.points.widgets.shortcut;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.bruce.points.R;
import com.bruce.points.ui.main.MainActivity;

public class ShortcutBigAppWidget extends AppWidgetProvider {

    private RemoteViews mRemoteViews;


    //第一次被添加
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.i("hzw", "onEnabled");
    }

    //第一次被添加或者大小发生变化
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Log.i("hzw", "onAppWidgetOptionsChanged = " + newOptions.toString());
    }

    //被删除
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.i("hzw", "onDeleted");
    }

    //最后一个被删除时调用
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.i("hzw", "onDisabled");
    }

    //更新
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i("hzw", "onUpdate");
        for (int appWidgetId : appWidgetIds) {
            Log.i("hzw", "onUpdate = " + appWidgetId);
            mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.shortcut_layout_big);
            Intent mainIntent = new Intent(context, MainActivity.class);
            PendingIntent mainPendingIntent = PendingIntent.getActivity(context, 200, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mRemoteViews.setOnClickPendingIntent(R.id.iv_shortcut_image, mainPendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, mRemoteViews);
        }
    }
}
