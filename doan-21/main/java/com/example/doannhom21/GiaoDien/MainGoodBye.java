package com.example.doannhom21.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.doannhom21.R;

public class MainGoodBye extends AppCompatActivity {
    ImageView imgViewBye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_good_bye);

        setControl();
        setEvent();
    }

    private void setControl() {
        imgViewBye = findViewById(R.id.imgGoodBye);
    }

    private void setEvent() {

        //Hoạt cảnh Image
        Animation animation = AnimationUtils.loadAnimation(MainGoodBye.this,R.anim.endtotop);
        imgViewBye.startAnimation(animation);

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