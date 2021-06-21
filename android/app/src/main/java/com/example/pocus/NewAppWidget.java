package com.example.pocus;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,int appWidgetId) {

        //CharSequence widgetText = " KEYWORD \n SMS";

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        //views.setTextViewText(R.id.widget_test_textview, " KEYWORD \n SMS");
        // 텍스트뷰 젤 위에꺼에다가 keyword랑 sms 넣을거야
        appWidgetManager.updateAppWidget(appWidgetId, views);
        // 지금 안되는게 xml 파일을 만질때마다 text 뷰가 ( 젤 위에 키워드랑 sms ) 됐다가 안됐다가 함
        // 왜 그런지를 모르겠음
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        // RemoteViewsService 실행 등록시키는 함수
        Intent serviceIntent = new Intent(context, MyRemoteViewsService.class);
        RemoteViews widget = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        widget.setRemoteAdapter(R.id.new_app_widget, serviceIntent);

//        클릭이벤트 인텐트 유보.
        //보내기
        appWidgetManager.updateAppWidget(appWidgetIds, widget);
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}