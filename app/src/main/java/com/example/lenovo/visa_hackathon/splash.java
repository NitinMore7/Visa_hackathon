package com.example.lenovo.visa_hackathon;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

public class splash extends AppCompatActivity {
  VideoView videoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        videoview=findViewById(R.id.videoview);

    }
    @Override
    public void onPause(){
        super.onPause();
        if(videoview.isPlaying())
            videoview.pause();
    }

    @Override
    public void onResume(){
        super.onResume();

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.b);
        videoview.setVideoURI(uri);
        videoview.setZOrderOnTop(true);
        videoview.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent i = new Intent(splash.this, MainActivity.class);
                    startActivity(i);
                    finish();

            }
        },3000);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Runtime.getRuntime().gc();
    }


}