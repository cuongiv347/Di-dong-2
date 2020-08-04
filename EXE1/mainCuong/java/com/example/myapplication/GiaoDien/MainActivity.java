package com.example.myapplication.GiaoDien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.CustomApdapter;
import com.example.myapplication.Adapter.MyRecyclerViewAdapter;
import com.example.myapplication.DataBase.DBCongTrinh;
import com.example.myapplication.Model.CardViewModel;
import com.example.myapplication.Model.CongTrinh;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    EditText txtMaCT, txtTenCT, txtDiaChi;
    Button btnThem, btnXoa, btnSua, btnThoat, btnShowAll;
    ListView lvDanhSachCT;
    Locale mMyLocale;
    CustomApdapter apdapter;
    ArrayList<CongTrinh> dataCT = new ArrayList<>();
    private Vector<CardViewModel> data;
    RecyclerView recyclerView;
    int position = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_lang_en:
                mMyLocale = new Locale("en", "US");
                Toast.makeText(MainActivity.this , "English" , Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_lang_vi:
                mMyLocale = new Locale("vi", "VN");
                Toast.makeText(MainActivity.this , "Tiếng Việt" , Toast.LENGTH_LONG).show();
                break;
        }
        onChangLanguage(mMyLocale);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
//        HienThiDL();
        LayDLRecycler();
        //Setup Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(R.layout.card_view_layout, data);
        recyclerView.setAdapter(adapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDL();
//                HienThiDL();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CongTrinh congTrinh = getCongTrinh();
                DBCongTrinh dbCongTrinh = new DBCongTrinh(getApplicationContext());
                dbCongTrinh.Xoa(congTrinh);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CongTrinh congTrinh = getCongTrinh();
                DBCongTrinh dbCongTrinh = new DBCongTrinh(getApplicationContext());
                dbCongTrinh.Sua(congTrinh);
            }
        });

        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainGridView.class);
                startActivity(intent);
            }
        });
    }

    private CongTrinh getCongTrinh() {
        CongTrinh congTrinh = new CongTrinh();
        congTrinh.setMaCT(txtMaCT.getText().toString());
        congTrinh.setTenCT(txtTenCT.getText().toString());
        congTrinh.setDiaChiCT(txtDiaChi.getText().toString());
        return congTrinh;
    }

    private void LayDLRecycler() {
        data = new Vector<CardViewModel>();
        data.add(new CardViewModel("CT001", "Trường Học", "TP.HCM", R.drawable.abc));

    }

    private void HienThiDL() {
        DBCongTrinh dbCongTrinh = new DBCongTrinh(this);
        dataCT = dbCongTrinh.LayDL();
        apdapter = new CustomApdapter(MainActivity.this, R.layout.card_view_layout, dataCT);
        lvDanhSachCT.setAdapter(apdapter);
    }

    private void ThemDL() {
        CongTrinh congTrinh = new CongTrinh();
        congTrinh.setMaCT(txtMaCT.getText().toString());
        congTrinh.setTenCT(txtTenCT.getText().toString());
        congTrinh.setDiaChiCT(txtDiaChi.getText().toString());
        DBCongTrinh dbCongTrinh = new DBCongTrinh(this);
        dbCongTrinh.Them(congTrinh);
    }

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

    private void setControl() {
        txtMaCT = findViewById(R.id.txtMaCT);
        txtTenCT = findViewById(R.id.txtTenCT);
        txtDiaChi = findViewById(R.id.txtDiaChiCT);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnThoat = findViewById(R.id.btnThoat);
        btnShowAll = findViewById(R.id.showall);
//        lvDanhSachCT = findViewById(R.id.lvDanhSachCT);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

}