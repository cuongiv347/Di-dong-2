package com.example.doannhom21.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.doannhom21.R;

public class MainLoad extends AppCompatActivity {

    ImageView imgCar, imgMatTroi ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_load);

        setControl();
        setEvent();

    }

    private void setControl() {
        imgCar = findViewById(R.id.imgLoad);
        imgMatTroi = findViewById(R.id.imgMatTroi);
    }


    private void setEvent() {

        //Chuyển ảnh liên tiếp
        final AnimationDrawable runCar = (AnimationDrawable) imgCar.getDrawable();
        runCar.start();

        //Hoạt cảnh Mặt trời
        Animation animation = AnimationUtils.loadAnimation(MainLoad.this,R.anim.mixed_anim);
        imgMatTroi.startAnimation(animation);

        //Thời gian kết thúc Hoạt cảnh
        final long SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainLoad.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}