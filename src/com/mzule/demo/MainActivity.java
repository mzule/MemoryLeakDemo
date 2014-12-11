package com.mzule.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final HexagonView hexagonView = new HexagonView(getApplicationContext());
        setContentView(hexagonView);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                hexagonView.setData(new float[]{0.84f, 0.79f, 0.90f, 0.71f, 0.72f, 0.53f});
            }
        }, 2000);
        hexagonView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                hexagonView.setData(null);
                hexagonView.postInvalidate();
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        hexagonView.setData(new float[]{0.84f, 0.79f, 0.90f, 0.71f, 0.72f, 0.53f});
                    }
                }, 2000);
            }
        });
    }

}
