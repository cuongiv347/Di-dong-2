package com.example.myapplication.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.DataBase.DBCongTrinh;
import com.example.myapplication.Model.CongTrinh;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MainDetailCT extends AppCompatActivity {
    EditText txtMaCT, txtTenCT, txtDiaChiCT;
    Button btnUpdata;
    ArrayList<CongTrinh> dataCT = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail_ct);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setControl();
        setEvent();
    }

    private void setEvent() {
        showData();

        btnUpdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CongTrinh congTrinh = getCongTrinh();
                DBCongTrinh dbCongTrinh = new DBCongTrinh(getApplicationContext());
                dbCongTrinh.Sua(congTrinh);
                Intent intent = new Intent(MainDetailCT.this , MainGridView.class);
                startActivity(intent);
            }
        });
    }

    private void showData(){
        String mact = getIntent().getExtras().getString("mact");
        DBCongTrinh dbCongTrinh = new DBCongTrinh(this);
        dataCT = dbCongTrinh.LayDL(mact);
        txtMaCT.setText(dataCT.get(0).getMaCT());
        txtTenCT.setText(dataCT.get(0).getTenCT());
        txtDiaChiCT.setText(dataCT.get(0).getDiaChiCT());
    }

    private CongTrinh getCongTrinh() {
        CongTrinh congTrinh = new CongTrinh();
        congTrinh.setMaCT(txtMaCT.getText().toString());
        congTrinh.setTenCT(txtTenCT.getText().toString());
        congTrinh.setDiaChiCT(txtDiaChiCT.getText().toString());
        return congTrinh;
    }

    @SuppressLint("WrongViewCast")
    private void setControl() {
        txtMaCT = findViewById(R.id.txtMaCT);
        txtTenCT = findViewById(R.id.txtTenCT);
        txtDiaChiCT = findViewById(R.id.txtDiaChiCT);
        btnUpdata = findViewById(R.id.btnUpdata);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                Intent intent = new Intent(MainDetailCT.this , MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}