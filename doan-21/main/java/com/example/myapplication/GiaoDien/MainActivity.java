package com.example.myapplication.GiaoDien;

import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    boolean lang = true;


    Button btnVatTu, btnCongTrinh, btnPhieu, btnChiTietPhieu, btnExit;
    ImageView imgLogo;
    Locale mMyLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    private void setEvent() {

        //Hoạt cảnh LOGO
        final Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.blink
        );
        imgLogo.startAnimation(animation);

        //Hoạt cảnh chuyển trang
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIcon(R.anim.imgclick);
            }
        });
        btnVatTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hoạt cảnh Button Chuyển trang
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
                btnVatTu.startAnimation(animation1);
                Intent intent = new Intent(MainActivity.this, MainVatTu.class);
                startActivity(intent);
            }
        });
        btnChiTietPhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hoạt cảnh Button Chuyển trang
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
                btnChiTietPhieu.startAnimation(animation1);
                Intent intent = new Intent(MainActivity.this, MainChiTietPhieu.class);
                startActivity(intent);
            }
        });
        btnPhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hoạt cảnh Button Chuyển trang
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
                btnPhieu.startAnimation(animation1);
                Intent intent = new Intent(MainActivity.this, MainPhieu.class);
                startActivity(intent);
            }
        });
        btnCongTrinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hoạt cảnh Button Chuyển trang
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
                btnCongTrinh.startAnimation(animation1);
                Intent intent = new Intent(MainActivity.this, MainCongTrinh.class);
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Hoạt cảnh Button Chuyển trang
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.lefttoright);
                btnExit.startAnimation(animation1);

                //Thông báo đóng trang
                final AlertDialog.Builder aler = new AlertDialog.Builder(MainActivity.this);
                aler.setTitle("THÔNG BÁO");
                aler.setMessage("Bạn có muốn thoát ?");
                aler.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                aler.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, MainExit.class);
                        startActivity(intent);
                        finish();
                    }
                });
                aler.show();
            }
        });
    }


    private void setIcon(@AnimRes int id) {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, id);
        imgLogo.startAnimation(animation);
    }

    private void setControl() {
        btnChiTietPhieu = findViewById(R.id.btnChiTietPhieu);
        btnPhieu = findViewById(R.id.btnPhieu);
        btnCongTrinh = findViewById(R.id.btnCongTrinh);
        btnVatTu = findViewById(R.id.btnVatTu);
        imgLogo = findViewById(R.id.imgLogo);
        btnExit = findViewById(R.id.btnExit);
    }

    //Ngôn Ngữ
    private void onChangLanguage(Locale locale) {
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
        Configuration configuration = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        getBaseContext().getResources().updateConfiguration(configuration, displayMetrics);
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //Khai báo MENU
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //MENU Ngôn ngữ
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_lang_en:
                mMyLocale = new Locale("en", "US");
                break;
            case R.id.menu_lang_vi:
                mMyLocale = new Locale("vi", "VN");
                break;
        }
        onChangLanguage(mMyLocale);
        return super.onOptionsItemSelected(item);
    }
}
