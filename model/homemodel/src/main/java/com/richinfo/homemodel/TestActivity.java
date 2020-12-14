package com.richinfo.homemodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.richinfo.homemodel.activity.splash.SplashActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homemodel_activity_test);
    }

    public void startHome(View view) {

        Intent intent = new Intent().setClass(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }
}