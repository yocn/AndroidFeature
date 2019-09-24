package com.yocn.af;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yocn.af.view.splash.MySplashActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mSplashBtn = findViewById(R.id.btn_splash);
        mSplashBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_splash:
                startActivity(new Intent(MainActivity.this, MySplashActivity.class));
                break;
            default:
        }
    }
}
