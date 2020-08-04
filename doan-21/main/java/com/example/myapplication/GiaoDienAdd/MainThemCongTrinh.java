package com.example.myapplication.GiaoDienAdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.CongTrinh;
import com.example.myapplication.R;
import com.example.myapplication.DataBase.DBCongTrinh;
import com.example.myapplication.GiaoDien.MainCongTrinh;

public class MainThemCongTrinh extends AppCompatActivity {

    EditText edtMaCT, edtTenCT , edtDiaChiCT;
    Button btnThem, btnXoa, btnSua, btnClear;
    DBCongTrinh dbCongTrinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_congtrinh);
        setControl();
        setEvent();
    }

    private void setEvent() {

        //Hoạt cảnh chuyển trang
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        //khai báo menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        dbCongTrinh = new DBCongTrinh(getApplicationContext());
        if (getIntent().getExtras() != null) {
            setCongTrinh();
            btnThem.setEnabled(false);
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
        }
        else {
            btnThem.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
        }
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCongTrinh().getMaCongTrinh().equals("")
                        || getCongTrinh().getTenCongTrinh().equals("")
                        || getCongTrinh().getDiaChiCongTrinh().equals("")){
                    Toast.makeText(getApplication(), "Co truong rong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbCongTrinh.Them(getCongTrinh()) == 0) {
                    Toast.makeText(getApplication(), "Thêm sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemCongTrinh.this, MainCongTrinh.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbCongTrinh.Xoa(getCongTrinh()) != 0) {
                    Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainThemCongTrinh.this, MainCongTrinh.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Xóa không thành công!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (dbCongTrinh.Sua(getCongTrinh()) != 0) {
                    Toast.makeText(getApplication(), "Sửa sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemCongTrinh.this, MainCongTrinh.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm không tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaCT.setText("");
                edtTenCT.setText("");
                edtDiaChiCT.setText("");
            }
        });

    }

    private void setCongTrinh() {
        Bundle bundle = getIntent().getExtras();
        String val = bundle.getString("index");
        String[] congTrinh = val.split("-");

        edtMaCT.setText(congTrinh[0]);
        edtTenCT.setText(congTrinh[1]);
        edtDiaChiCT.setText(congTrinh[2]);
    }

    private CongTrinh getCongTrinh() {
        CongTrinh pb = new CongTrinh();
        pb.setMaCongTrinh(edtMaCT.getText().toString());
        pb.setTenCongTrinh(edtTenCT.getText().toString());
        pb.setDiaChiCongTrinh(edtDiaChiCT.getText().toString());
        return pb;
    }

    private void setControl() {
        edtMaCT = findViewById(R.id.edtMaCongTrinh);
        edtTenCT = findViewById(R.id.edtTenCongTrinh);
        edtDiaChiCT = findViewById(R.id.edtDiaChiCongTrinh);
        btnThem = findViewById(R.id.btnThemCongTrinh);
        btnClear = findViewById(R.id.btnClearCongTrinh);
        btnXoa = findViewById(R.id.btnXoaCongTrinh);
        btnSua = findViewById(R.id.btnSuaCongTrinh);
    }
}
