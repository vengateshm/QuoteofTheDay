package com.falcon.quoteoftheday.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.falcon.quoteoftheday.R;

public class SplashActivity extends AppCompatActivity {

    private Handler splashHandler = new Handler();

    private static final int TIMEOUT = 3000;// 2 secs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startSplashScreenTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeSplashScreenTimer();
    }

    private void startSplashScreenTimer() {
        splashHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                removeSplashScreenTimer();

                // After 2 secs open home activity
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIMEOUT);
    }

    private void removeSplashScreenTimer() {
        if (splashHandler != null) {
            splashHandler.removeCallbacksAndMessages(null);
        }
    }
}
