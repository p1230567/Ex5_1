package com.cyo.ex5_1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by USER on 2015/4/11.
 */
public class notification extends Activity {
    private TextView tv_title, tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        findViews();
        showInfo();
    }

    private void findViews() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);

    }

    private void showInfo() {
        Bundle bundle = this.getIntent().getExtras();
        tv_content.setText(bundle.getString("content"));


    }

}
