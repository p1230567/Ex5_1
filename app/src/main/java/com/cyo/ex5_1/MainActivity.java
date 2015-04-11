package com.cyo.ex5_1;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
    private Button btn_submit, btn_cancel;
    private TextView tv_result;
    private EditText et_submit;
    private final static int NOTIFICATION_ID = 100;
    private NotificationManager notificationManager;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        findView();

    }

    private void findView() {
        et_submit = (EditText) findViewById(R.id.et_submit);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = et_submit.getText().toString();
                Intent intent = new Intent(MainActivity.this, notification.class);
                Bundle bundle = new Bundle();
                bundle.putString("content", content);
                intent.putExtras(bundle);
                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this
                        , 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                Notification notification = new Notification.Builder(MainActivity.this)
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
                                //加入狀態列下拉後的進一步操作
                        .setSound(soundUri)
                        .build();
                //呼叫notify()送出通知訊息
                notificationManager.notify(NOTIFICATION_ID, notification);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            tv_result.setText(content);
                        } catch (Exception e) {
                        }

                    }
                }).start();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_submit.setText("");
                notificationManager.cancel(NOTIFICATION_ID);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
