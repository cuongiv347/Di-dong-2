package com.example.doannhom21.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.doannhom21.Adapter.CustomAdapter;
import com.example.doannhom21.DataBase.DataCongTrinh;
import com.example.doannhom21.DataBase.DataVatTu;
import com.example.doannhom21.Model.CongTrinh;
import com.example.doannhom21.Model.VatTu;
import com.example.doannhom21.R;

import java.util.ArrayList;

public class MainVatTu extends AppCompatActivity {

    EditText edtMaVatTu, edtTenVatTu, ediGiaVanChuyen;
    Spinner spDVTinh;
    Button btnThem, btnXoa, btnSua, btnClear;
    ListView lvVatTu;

    ArrayList<String> dataDVTinh;
    ArrayList<VatTu> adapter;

    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vat_tu);

        setControl();
        setEvent();
    }

    private void setEvent() {
        khoiTao();
        //Hoạt cảnh chuyển trang
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        //khai báo menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        final DataVatTu dataVatTu=new DataVatTu(MainVatTu.this);

        adapter= new ArrayList<VatTu>(MainVatTu.this, android.R.layout.simple_list_item_1, dataVatTu.getAllVatTu());
        lvVatTu.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataVatTu.themVatTu(getVatTu());
                adapter.clear();
                adapter.addAll(dataVatTu.getAllVatTu());
                adapter.notifyDataSetChanged();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataVatTu.xoaVatTu(getVatTu());
                adapter.clear();
                adapter.addAll(dataVatTu.getAllVatTu());
                adapter.notifyDataSetChanged();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataVatTu.capNhatVatTu(getVatTu());
                adapter.clear();
                adapter.addAll(dataVatTu.getAllVatTu());
                adapter.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaTatCaNhap();
            }
        });

    }

    private void setControl() {
        edtMaVatTu = findViewById(R.id.edtMaVT);
        edtTenVatTu = findViewById(R.id.edtTenVT);
        ediGiaVanChuyen = findViewById(R.id.edtGiaVT);
        spDVTinh = findViewById(R.id.spDonViTinh);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
        lvVatTu = findViewById(R.id.lvVatTu);
    }

    //Menu BackHome
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void khoiTao() {
        dataDVTinh.add("Việt Nam");
        dataDVTinh.add("Mỹ");
        dataDVTinh.add("Hàn");
    }

    private VatTu getVatTu() {
        String giavanChuyen = ediGiaVanChuyen.getText().toString();
        String tenTB = edtTenVatTu.getText().toString();
        String dvTinh = spDVTinh.getSelectedItem().toString();
        String maVT = edtMaVatTu.getText().toString();
        VatTu device = new VatTu(0, maVT, tenTB, dvTinh, giavanChuyen);
        return device;
    }
}