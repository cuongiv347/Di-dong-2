package com.example.doannhom21.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.doannhom21.DataBase.DataCongTrinh;
import com.example.doannhom21.Model.CongTrinh;
import com.example.doannhom21.R;

public class MainCongTrinh extends AppCompatActivity {

    EditText edtMaCongTrinh , edtTenCongTrinh , edtDiaChi;
    Button btnThem , btnXoa , btnSua , btnClear;
    ListView lvShow;
    ArrayAdapter<CongTrinh> adapter;
    int index=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cong_trinh);

        setControl();
        setEvent();
    }

    private void setEvent() {

        //Hoạt cảnh chuyển trang
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        //khai báo menu quay về trang đầu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final DataCongTrinh dataCongTrinh=new DataCongTrinh(MainCongTrinh.this);

        adapter=new ArrayAdapter<CongTrinh>(MainCongTrinh.this,android.R.layout.simple_list_item_1,
                dataCongTrinh.getAllCongTrinh());
        lvShow.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataCongTrinh.themCongTrinh(getCongTrinh());
                adapter.clear();
                adapter.addAll(dataCongTrinh.getAllCongTrinh());
                adapter.notifyDataSetChanged();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCongTrinh.xoaCongTrinh(getCongTrinh());
                adapter.clear();
                adapter.addAll(dataCongTrinh.getAllCongTrinh());
                adapter.notifyDataSetChanged();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataCongTrinh.capNhatCongTrinh(getCongTrinh());
                adapter.clear();
                adapter.addAll(dataCongTrinh.getAllCongTrinh());
                adapter.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaTatCaNhap();
            }
        });

        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtMaCongTrinh.setText(dataCongTrinh.getAllCongTrinh().get(position).getMaCT());
                edtTenCongTrinh.setText(dataCongTrinh.getAllCongTrinh().get(position).getTenCT());
                edtDiaChi.setText(dataCongTrinh.getAllCongTrinh().get(position).getDiaChiCT());
                index=position;
            }
        });
    }

    private void setControl() {
        edtMaCongTrinh = findViewById(R.id.edtMaCT);
        edtTenCongTrinh = findViewById(R.id.edtTenCT);
        edtDiaChi = findViewById(R.id.edtDiaChiCT);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
        lvShow = findViewById(R.id.lvShow);
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

//    private CongTrinh getCongTrinhs(){
//        String maCT=edtMaCongTrinh.getText().toString();
//        String tenCT=edtTenCongTrinh.getText().toString();
//        String diaChiCT=edtDiaChi.getText().toString();
//        return new CongTrinh(maCT,tenCT,diaChiCT);
//    }
    private void xoaTatCaNhap(){
        edtMaCongTrinh.setText("");
        edtTenCongTrinh.setText("");
        edtDiaChi.setText("");
    }
    private CongTrinh getCongTrinh(){
        String maCT=edtMaCongTrinh.getText().toString();
        String tenCT=edtTenCongTrinh.getText().toString();
        String diaChiCT=edtDiaChi.getText().toString();
        return new CongTrinh(maCT,tenCT,diaChiCT);
    }



}