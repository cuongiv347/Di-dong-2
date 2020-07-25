package com.example.doannhom21.GiaoDien;

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
import android.widget.Spinner;

import com.example.doannhom21.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btnCongTrinh, btnVatTu, btnPhieuVC, btnCTPhieuVC, btnExit;
    Locale mMyLocale;
    ImageView imgLogo;

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
                getApplicationContext(),R.anim.blink
        );
        imgLogo.startAnimation(animation);

        //Hoạt cảnh chuyển trang
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        //Button Công trình
        btnCongTrinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Hoạt cảnh Button Chuyển trang
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
                btnCongTrinh.startAnimation(animation1);

                //Chuyển trang
                Intent intent = new Intent(MainActivity.this, MainCongTrinh.class);
                startActivity(intent);

            }
        });

        //Button Vật tư
        btnVatTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Hoạt cảnh Button Chuyển trang
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
                btnVatTu.startAnimation(animation1);

                //Chuyển trang
                Intent intent = new Intent(MainActivity.this, MainVatTu.class);
                startActivity(intent);
            }
        });

        //Button Phiếu vận chuyển
        btnPhieuVC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Hoạt cảnh Button Chuyển trang
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
                btnPhieuVC.startAnimation(animation1);

                //Chuyển trang
                Intent intent = new Intent(MainActivity.this, MainPhieuVanChuyen.class);
                startActivity(intent);
            }
        });

        //Button Chi tiết phiếu vận chuyển
        btnCTPhieuVC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Hoạt cảnh Button Chuyển trang
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
                btnCTPhieuVC.startAnimation(animation1);

                //Chuyển trang
                Intent intent = new Intent(MainActivity.this, MainChiTietPhieuVanChuyen.class);
                startActivity(intent);
            }
        });

        //Button Exit
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Hoạt cảnh Button Chuyển trang
                Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
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
                        Intent intent = new Intent(MainActivity.this , MainGoodBye.class);
                        startActivity(intent);
                        finish();
                    }
                });
                aler.show();
            }
        });
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
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //MENU Ngôn ngữ
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

    private void setControl() {
        imgLogo = findViewById(R.id.imgLogo);
        btnCongTrinh = findViewById(R.id.btnCongtrinh);
        btnVatTu = findViewById(R.id.btnVatTu);
        btnPhieuVC = findViewById(R.id.btnPhieuVC);
        btnCTPhieuVC = findViewById(R.id.btnCTPhieuVC);
        btnExit = findViewById(R.id.btnExit);
    }
}