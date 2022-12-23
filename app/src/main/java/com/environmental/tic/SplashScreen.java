package com.environmental.tic;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {
    Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ImageView avd = findViewById(R.id.avd_splash);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    AnimatedVectorDrawable avd1 = (AnimatedVectorDrawable) avd.getDrawable();
                    avd1.start();
                }
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        },3000);
    }
}
