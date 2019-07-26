package com.cqkj.snail.system.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.cqkj.snail.R;
import com.fxkj.publicframework.tool.SpUtils;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 闪屏页
 *
 * @author wwb 2019/01/08
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startMain();
    }

    private void startMain() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };
        timer.schedule(timerTask, 2000);
    }
}
