package com.example.virtualclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {
    Handler h=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent i = new Intent(SplashScreenActivity.this,Signup_Screen_Activity.class);
//                startActivity(i);
                startActivity(new Intent(getApplicationContext(), Login_Screen_Activity.class));
                finish();

            }
        },2500);
    }
}