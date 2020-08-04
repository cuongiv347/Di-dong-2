package com.example.myapplication.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.myapplication.R;

public class MainExit extends AppCompatActivity {
    ImageView imgViewExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exit);

        setControl();
        setEvent();
    }

    private void setControl() {
        imgViewExit = findViewById(R.id.imgExit);
    }

    private void setEvent() {

        //Hoạt cảnh Image
        Animation animation = AnimationUtils.loadAnimation(MainExit.this,R.anim.endtotop);
        imgViewExit.startAnimation(animation);

        //Thời gian kết thúc hoạt cảnh
        final long SPLASH_TIME_OUT = 2500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}