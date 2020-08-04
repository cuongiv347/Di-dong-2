package com.example.myapplication.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Model.ChiTietPhieu;
import com.example.myapplication.Adapter.CustomAdapterChiTietPhieu;
import com.example.myapplication.R;
import com.example.myapplication.DataBase.DBChiTietPhieu;
import com.example.myapplication.GiaoDienAdd.MainThemChiTietPhieu;

public class MainChiTietPhieu extends AppCompatActivity {

    boolean check = false;
    DBChiTietPhieu dbChiTietPhieu;
    CustomAdapterChiTietPhieu adapterChiTietPhieu;
    Button btnThem, btnIndex, btnTK;
    EditText edtTK;
    ListView lvChiTietPhieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chitietphieu);
        setControl();
        setEvent();
    }

    private void setEvent() {

        //Hoạt cảnh chuyển trang
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        //khai báo menu
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        LoadData();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainThemChiTietPhieu.class);
                startActivity(intent);
            }
        });
        btnIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tk();
                check = true;
            }
        });

        lvChiTietPhieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(check){
                    ChiTietPhieu chiTietPhieu = dbChiTietPhieu.TiemKiem(edtTK.getText().toString()).get(position);
                    String sp = chiTietPhieu.getSoPhieu() +"-"+ chiTietPhieu.getCuLy()
                            +"-"+ chiTietPhieu.getMaPhieu() +"-"+ chiTietPhieu.getMaVatTu() +"-"+ chiTietPhieu.getSoLuong();
                    Intent intent = new Intent(MainChiTietPhieu.this, MainThemChiTietPhieu.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("index", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    ChiTietPhieu chiTietPhieu = dbChiTietPhieu.LayDL().get(position);
                    String sp = chiTietPhieu.getSoPhieu() +"-"+ chiTietPhieu.getCuLy()
                            +"-"+ chiTietPhieu.getMaPhieu() +"-"+ chiTietPhieu.getMaVatTu() +"-"+ chiTietPhieu.getSoLuong();
                    Intent intent = new Intent(MainChiTietPhieu.this, MainThemChiTietPhieu.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("index", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        lvChiTietPhieu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                if(check){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainChiTietPhieu.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbChiTietPhieu.Xoa(dbChiTietPhieu.TiemKiem(edtTK.getText().toString()).get(position)) != 0) {
                                Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                                tk();
                            }
                            else{
                                Toast.makeText(getApplication(), "Xóa không thành công", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainChiTietPhieu.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbChiTietPhieu.Xoa(dbChiTietPhieu.LayDL().get(position)) != 0) {
                                Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                                LoadData();
                            }
                            else{
                                Toast.makeText(getApplication(), "Xóa không thành công", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                return false;
            }
        });
    }

    private void tk() {
        dbChiTietPhieu = new DBChiTietPhieu(this);
        adapterChiTietPhieu = new CustomAdapterChiTietPhieu(this, R.layout.list_chitietphieu,
                dbChiTietPhieu.TiemKiem(edtTK.getText().toString()) );
        lvChiTietPhieu.setAdapter(adapterChiTietPhieu);
    }

    private void LoadData() {
        dbChiTietPhieu = new DBChiTietPhieu(this);
        adapterChiTietPhieu = new CustomAdapterChiTietPhieu(this, R.layout.list_chitietphieu, dbChiTietPhieu.LayDL() );
        lvChiTietPhieu.setAdapter(adapterChiTietPhieu);
    }

    private void setControl() {
        btnTK = findViewById(R.id.btnTKChiTietPhieu);
        edtTK = findViewById(R.id.edtTKChiTietPhieu);
        btnThem = findViewById(R.id.btnThemChiTietPhieu);
        btnIndex = findViewById(R.id.btnBack);
        lvChiTietPhieu = findViewById(R.id.lvChiTietPhieu);
    }
}
