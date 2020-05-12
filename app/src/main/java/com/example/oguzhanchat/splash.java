package com.example.oguzhanchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class splash extends AppCompatActivity {
    ImageView imageView;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        imageView=(ImageView)findViewById(R.id.imf);
        imageView.setBackgroundResource(R.drawable.anim);
        animationDrawable=(AnimationDrawable)imageView.getBackground();
        animationDrawable.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splash.this,MainActivity.class));
                finish();
            }
        },3000);
    }
}
