package com.mzule.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MyActivity extends Activity {

    private Handler handler;
    private TextView textView;
    private int index;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my);
        textView = (TextView) findViewById(R.id.textView);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                index++;
                textView.append(String.valueOf(index));
                sendUpdateMessage();
            }
        };
        sendUpdateMessage();
    }

    void sendUpdateMessage() {
        Message message = new Message();
        message.what = 0;
        handler.sendMessageDelayed(message, 1000);
    }

}
