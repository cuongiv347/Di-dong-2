package com.example.myapplication.GiaoDienAdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Model.VatTu;
import com.example.myapplication.R;
import com.example.myapplication.DataBase.DBVatTu;
import com.example.myapplication.GiaoDien.MainVatTu;


public class MainThemVatTu extends AppCompatActivity {

    EditText edtMaVT, edtTenVT, edtDVT, edtGiaVC;
    Button btnThem, btnXoa, btnSua, btnClear;
    DBVatTu dbVatTu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_vattu);

        setControl();
        setEvent();
    }

    private void setEvent() {

        //Hoạt cảnh chuyển trang
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        //khai báo menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbVatTu = new DBVatTu(getApplicationContext());
        if (getIntent().getExtras() != null) {
            setVatTu();
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
                if(getVatTu().getMaVatTu().equals("") || getVatTu().getTenVatTu().equals("")
                        || getVatTu().getDvTinh().equals("") || getVatTu().getGiaVanChuyen().equals("")){
                    Toast.makeText(getApplication(), "Co truong rong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbVatTu.Them(getVatTu()) == 0) {
                    Toast.makeText(getApplication(), "Thêm sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemVatTu.this, MainVatTu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbVatTu = new DBVatTu(getApplicationContext());
                if (dbVatTu.Xoa(getVatTu()) != 0) {
                    Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainThemVatTu.this, MainVatTu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Xóa không thành công!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (dbVatTu.Sua(getVatTu()) != 0) {
                    Toast.makeText(getApplication(), "Sửa sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemVatTu.this, MainVatTu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm không tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaVT.setText("");
                edtTenVT.setText("");
                edtGiaVC.setText("");
                edtDVT.setText("");
            }
        });

    }

    private void setVatTu() {
        Bundle bundle = getIntent().getExtras();
        String val = bundle.getString("index");
        String[] vpp = val.split("-");

        edtMaVT.setText(vpp[0]);
        edtTenVT.setText(vpp[1]);
        edtDVT.setText(vpp[2]);
        edtGiaVC.setText(vpp[3]);
    }

    private VatTu getVatTu() {
        VatTu vatTu = new VatTu();
        vatTu.setMaVatTu(edtMaVT.getText().toString());
        vatTu.setTenVatTu(edtTenVT.getText().toString());
        vatTu.setDvTinh(edtDVT.getText().toString());
        vatTu.setGiaVanChuyen(edtGiaVC.getText().toString());
        return vatTu;
    }

    private void setControl() {
        edtMaVT = findViewById(R.id.edtMaVatTu);
        edtDVT = findViewById(R.id.edtDVTinh);
        edtGiaVC = findViewById(R.id.edtGiaVC);
        edtTenVT = findViewById(R.id.edtTenVatTu);
        btnThem = findViewById(R.id.btnThemVatTu);
        btnClear = findViewById(R.id.btnClearVatTu);
        btnXoa = findViewById(R.id.btnXoaVatTu);
        btnSua = findViewById(R.id.btnSuaVatTu);
    }
}
