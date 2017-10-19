package com.example.tianqitong.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.tianqitong.R;
import com.example.tianqitong.dao.Iservice;

import java.util.HashMap;


/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class ShowNotification_Service extends Service {
    private NotificationManager manager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    void show(HashMap<String,String> map){
        manager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
//        创建Notification对象
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.activity_notification);
//        替代控件上的内容
        remoteViews.setTextViewText(R.id.noti_bar_city,map.get("city"));
        remoteViews.setTextViewText(R.id.noti_bar_weather,map.get("temp")+"℃"+"  "+map.get("weather"));


//        设置Notification
        builder.setContent(remoteViews);
        builder.setTicker("正在播报......")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(PendingIntent.getActivity(this, 1, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT))
                .setOngoing(true);//当前事件是否正在进行

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        manager.notify(1000,notification);
    }
    void cancel(){
        if (manager!=null) {
            manager.cancelAll();
        }
    }
    class MyBinder extends Binder implements Iservice {

        @Override
        public void showNotification(HashMap<String, String> map) {
            show(map);
        }

        @Override
        public void cancelNotification() {
            cancel();
        }
    }
}
