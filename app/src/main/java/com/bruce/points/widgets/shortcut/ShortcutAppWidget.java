package com.bruce.points.widgets.shortcut;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.bruce.points.R;
import com.bruce.points.ui.MainActivity;

public class ShortcutAppWidget extends AppWidgetProvider {

    private RemoteViews mRemoteViews;


    //第一次被添加
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

    }

    //第一次被添加或者大小发生变化
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    //被删除
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

    }

    //最后一个被删除时调用
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    //更新
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.shortcut_layout);
        Intent mainIntent = new Intent(context, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.iv_shortcut_image, mainPendingIntent);
    }
}
