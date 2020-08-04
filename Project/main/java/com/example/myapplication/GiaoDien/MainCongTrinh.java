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

import com.example.myapplication.Model.CongTrinh;
import com.example.myapplication.Adapter.CustomAdapterCongTrinh;
import com.example.myapplication.R;
import com.example.myapplication.DataBase.DBCongTrinh;
import com.example.myapplication.GiaoDienAdd.MainThemCongTrinh;

public class MainCongTrinh extends AppCompatActivity {

    boolean check = false;
    DBCongTrinh dbCongTrinh;
    CustomAdapterCongTrinh adapterCongTrinh;
    Button btnThem, btnIndex, btnTK;
    EditText edtTK;
    ListView lvCongtrinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_congtrinh);
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
                Intent intent = new Intent(getApplicationContext(), MainThemCongTrinh.class);
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

        lvCongtrinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(check){
                    CongTrinh congTrinh = dbCongTrinh.TimKiem(edtTK.getText().toString()).get(position);
                    String sp = congTrinh.getMaCongTrinh() +"-"+ congTrinh.getTenCongTrinh() +"-"+ congTrinh.getDiaChiCongTrinh();
                    Intent intent = new Intent(MainCongTrinh.this, MainThemCongTrinh.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("index", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else
                {
                    CongTrinh congTrinh = dbCongTrinh.LayDL().get(position);
                    String sp = congTrinh.getMaCongTrinh() +"-"+ congTrinh.getTenCongTrinh() +"-"+ congTrinh.getDiaChiCongTrinh();
                    Intent intent = new Intent(MainCongTrinh.this, MainThemCongTrinh.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("index", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        lvCongtrinh.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                if(check){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainCongTrinh.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbCongTrinh.Xoa(dbCongTrinh.TimKiem(edtTK.getText().toString()).get(position)) != 0) {
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
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainCongTrinh.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbCongTrinh.Xoa(dbCongTrinh.LayDL().get(position)) != 0) {
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
        dbCongTrinh = new DBCongTrinh(this);
        adapterCongTrinh = new CustomAdapterCongTrinh(this, R.layout.list_congtrinh, dbCongTrinh.TimKiem(edtTK.getText().toString()) );
        lvCongtrinh.setAdapter(adapterCongTrinh);
    }

    private void LoadData() {
        dbCongTrinh = new DBCongTrinh(this);
        adapterCongTrinh = new CustomAdapterCongTrinh(this, R.layout.list_congtrinh, dbCongTrinh.LayDL() );
        lvCongtrinh.setAdapter(adapterCongTrinh);
    }

    private void setControl() {
        btnThem = findViewById(R.id.btnThemCongTrinh);
        btnIndex = findViewById(R.id.btnBack);
        lvCongtrinh = findViewById(R.id.lvCongTrinh);
        edtTK = findViewById(R.id.edtTKCongTrinh);
        btnTK = findViewById(R.id.btnTKCongTrinh);
    }
}
