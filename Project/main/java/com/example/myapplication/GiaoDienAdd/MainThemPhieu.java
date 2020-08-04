package com.example.myapplication.GiaoDienAdd;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Model.Phieu;
import com.example.myapplication.Model.CongTrinh;
import com.example.myapplication.R;
import com.example.myapplication.DataBase.DBPhieu;
import com.example.myapplication.DataBase.DBCongTrinh;
import com.example.myapplication.GiaoDien.MainPhieu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainThemPhieu extends AppCompatActivity {

    EditText edtMaPhieu, edtNgay;
    Spinner spMaCongTrinh;
    ImageView imgChonNgay;
    Button btnThem, btnXoa, btnSua, btnClear;
    DBPhieu dbPhieu;
    DBCongTrinh dbCongTrinh;
    ArrayList<String> data_CongTrinh = new ArrayList<>();
    ArrayAdapter adapter_CongTrinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_phieu);
        setControl();
        setEvent();
    }

    private void setEvent() {

        //Hoạt cảnh chuyển trang
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        //khai báo menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbPhieu = new DBPhieu(getApplicationContext());
        KhoiTaoSpinner();
        if (getIntent().getExtras() != null) {
            setPhieu();
            btnThem.setEnabled(false);
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
        }
        else {
            btnThem.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
        }

        imgChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonNgay();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getPhieu().getMaPhieu().equals("") ||
                        getPhieu().getNgay().equals("") || getPhieu().getMaCongTrinh().equals("")){
                    Toast.makeText(getApplication(), "Co truong rong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbPhieu.Them(getPhieu()) == 0) {
                    Toast.makeText(getApplication(), "Thêm sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemPhieu.this, MainPhieu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbPhieu.Xoa(getPhieu()) != 0) {
                    Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainThemPhieu.this, MainPhieu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Xóa không thành công!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (dbPhieu.Sua(getPhieu()) != 0) {
                    Toast.makeText(getApplication(), "Sửa sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemPhieu.this, MainPhieu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm không tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaPhieu.setText("");
                edtNgay.setText("");
                spMaCongTrinh.setSelection(0);
            }
        });

    }

    private void chonNgay(){
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtNgay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

    private void setPhieu() {
        Bundle bundle = getIntent().getExtras();
        String val = bundle.getString("index");
        String[] phieu = val.split("-");

        edtMaPhieu.setText(phieu[0]);
        edtNgay.setText(phieu[1]);
        spMaCongTrinh.setSelection(data_CongTrinh.indexOf(phieu[2]));
    }

    private void KhoiTaoSpinner(){
        dbCongTrinh = new DBCongTrinh(getApplicationContext());
        for (CongTrinh congTrinh: dbCongTrinh.LayDL()) {
            data_CongTrinh.add(congTrinh.getMaCongTrinh());
        }
        adapter_CongTrinh = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_CongTrinh);
        spMaCongTrinh.setAdapter(adapter_CongTrinh);
    }

    private Phieu getPhieu() {
        Phieu phieu = new Phieu();
        phieu.setMaPhieu(edtMaPhieu.getText().toString());
        phieu.setNgay(edtNgay.getText().toString());
        phieu.setMaCongTrinh(spMaCongTrinh.getSelectedItem().toString());
        return phieu;
    }

    private void setControl() {
        edtMaPhieu = findViewById(R.id.edtMaPhieu);
        edtNgay = findViewById(R.id.edtNgayPhieu);
        spMaCongTrinh = findViewById(R.id.spMaCongTring_Phieu);
        btnThem = findViewById(R.id.btnThemPhieu);
        btnClear = findViewById(R.id.btnClearPhieu);
        btnXoa = findViewById(R.id.btnXoaPhieu);
        btnSua = findViewById(R.id.btnSuaPhieu);
        imgChonNgay = findViewById(R.id.imgChonNgay);
    }
}
