package com.cyo.ex5_1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Created by USER on 2015/4/13.
 */
public class NotificationService extends Service {
    private NotificationManager notificationManager;
    private final static int NOTIFICATION_ID = 100;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//      服務啟動時執行通知
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Bundle bundle = intent.getExtras();
        String content = bundle.getString("content");
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(NotificationService.this
                , 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(NotificationService.this)
                //狀態列的文字
                .setTicker(getString(R.string.ticker))
                        //訊息面板的標題
                .setContentTitle(getString(R.string.tv_title))
                        //訊息面板的內容文字
                .setContentText(content)
                        //訊息的圖示
                .setSmallIcon(R.drawable.ic_secret_notification)
                        //點擊後會自動移除狀態列上的通知訊息
                .setAutoCancel(true)
                        //等使用者點了之後才會開啟指定的Activity
                .setContentIntent(pendingIntent)
                        //加入聲音
                .setSound(soundUri)
                .build();
        //呼叫notify()送出通知訊息
        notificationManager.notify(NOTIFICATION_ID, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
//當服務關閉時，將通知關閉
        notificationManager.cancel(NOTIFICATION_ID);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
