package com.example.myapplication.GiaoDienAdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Model.ChiTietPhieu;
import com.example.myapplication.Model.Phieu;
import com.example.myapplication.Model.VatTu;
import com.example.myapplication.R;
import com.example.myapplication.DataBase.DBChiTietPhieu;
import com.example.myapplication.DataBase.DBPhieu;
import com.example.myapplication.DataBase.DBVatTu;
import com.example.myapplication.GiaoDien.MainChiTietPhieu;

import java.util.ArrayList;

public class MainThemChiTietPhieu extends AppCompatActivity {

    EditText edtSoPhieu, edtCuLy, edtSoLuong;
    Spinner spMaPhieu, spMaVatTu;
    Button btnThem, btnXoa, btnSua, btnClear;
    DBChiTietPhieu dbChiTietPhieu;
    DBVatTu dbVatTu;
    DBPhieu dbPhieu;
    ArrayAdapter adapter_Phieu, adapter_VatTu;
    ArrayList<String> data_VatTu = new ArrayList<>(), data_Phieu = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_chitietphieu);
        setControl();
        setEvent();
    }

    private void setEvent() {

        //Hoạt cảnh chuyển trang
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        //khai báo menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbChiTietPhieu = new DBChiTietPhieu(getApplicationContext());
        KhoiTaoSpinner();
        if (getIntent().getExtras() != null) {
            setChiTietPhieu();
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
                if(getChiTietPhieu().getMaVatTu().equals("") || getChiTietPhieu().getSoPhieu().equals("")
                        || getChiTietPhieu().getCuLy().equals("") || getChiTietPhieu().getSoLuong().equals("")
                        || getChiTietPhieu().getMaPhieu().equals("")){
                    Toast.makeText(getApplication(), "Co truong rong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbChiTietPhieu.Them(getChiTietPhieu()) == 0) {
                    Toast.makeText(getApplication(), "Thêm sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemChiTietPhieu.this, MainChiTietPhieu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbChiTietPhieu.Xoa(getChiTietPhieu()) != 0) {
                    Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainThemChiTietPhieu.this, MainChiTietPhieu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Xóa không thành công!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (dbChiTietPhieu.Sua(getChiTietPhieu()) != 0) {
                    Toast.makeText(getApplication(), "Sửa sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemChiTietPhieu.this, MainChiTietPhieu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm không tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSoPhieu.setText("");
                edtCuLy.setText("");
                edtSoLuong.setText("");
                spMaPhieu.setSelection(0);
                spMaVatTu.setSelection(0);
            }
        });

    }

    private void setChiTietPhieu() {
        Bundle bundle = getIntent().getExtras();
        String val = bundle.getString("index");
        String[] show = val.split("-");

        edtSoPhieu.setText(show[0]);
        edtCuLy.setText(show[1]);
        spMaVatTu.setSelection(data_VatTu.indexOf(show[2]));
        spMaPhieu.setSelection(data_Phieu.indexOf(show[3]));
        edtSoLuong.setText(show[4]);
    }

    private  void KhoiTaoSpinner(){
        dbPhieu = new DBPhieu(getApplicationContext());
        dbVatTu = new DBVatTu(getApplicationContext());

        for (Phieu phieu: dbPhieu.LayDL()) {
            data_Phieu.add(phieu.getMaPhieu());
        }
        for (VatTu vatTu: dbVatTu.LayDL()) {
            data_VatTu.add(vatTu.getMaVatTu());
        }

        adapter_Phieu = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_Phieu);
        spMaPhieu.setAdapter(adapter_Phieu);
        adapter_VatTu = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_VatTu);
        spMaVatTu.setAdapter(adapter_VatTu);
    }

    private ChiTietPhieu getChiTietPhieu() {
        ChiTietPhieu chiTietPhieu = new ChiTietPhieu();
        chiTietPhieu.setSoPhieu(edtSoPhieu.getText().toString());
        chiTietPhieu.setCuLy(edtCuLy.getText().toString());
        chiTietPhieu.setSoLuong(edtSoLuong.getText().toString());
        chiTietPhieu.setMaVatTu(spMaVatTu.getSelectedItem().toString());
        chiTietPhieu.setMaPhieu(spMaPhieu.getSelectedItem().toString());
        return chiTietPhieu;
    }

    private void setControl() {
        edtSoPhieu = findViewById(R.id.edtSoPhieuCTP);
        edtCuLy = findViewById(R.id.edtCuLyCTP);
        edtSoLuong = findViewById(R.id.edtSoLuongCTP);
        spMaPhieu = findViewById(R.id.spMaPhieu_CTP);
        spMaVatTu = findViewById(R.id.spMaVatTu_CTP);
        btnThem = findViewById(R.id.btnThemCTP);
        btnClear = findViewById(R.id.btnClearCTP);
        btnXoa = findViewById(R.id.btnXoaCTP);
        btnSua = findViewById(R.id.btnSuaCTP);
    }
}
