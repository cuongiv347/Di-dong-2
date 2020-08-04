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

import com.example.myapplication.Model.Phieu;
import com.example.myapplication.Adapter.CustomAdapterPhieu;
import com.example.myapplication.R;
import com.example.myapplication.DataBase.DBPhieu;
import com.example.myapplication.GiaoDienAdd.MainThemPhieu;

public class MainPhieu extends AppCompatActivity {

    boolean check = false;
    DBPhieu dbPhieu;
    CustomAdapterPhieu adapterPhieu;
    Button btnThem, btnIndex, btnTK;
    EditText edtTK;
    ListView lvPhieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_phieu);
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
                Intent intent = new Intent(getApplicationContext(), MainThemPhieu.class);
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

        lvPhieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(check){
                    Phieu phieu = dbPhieu.TiemKiem(edtTK.getText().toString()).get(position);
                    String sp = phieu.getMaPhieu()
                            + "-" + phieu.getNgay() + "-" + phieu.getMaCongTrinh();
                    Intent intent = new Intent(MainPhieu.this, MainThemPhieu.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("index", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    Phieu phieu = dbPhieu.LayDL().get(position);
                    String sp = phieu.getMaPhieu()
                            + "-" + phieu.getNgay() + "-" + phieu.getMaCongTrinh();
                    Intent intent = new Intent(MainPhieu.this, MainThemPhieu.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("index", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        lvPhieu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                if(check){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainPhieu.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dbPhieu.Xoa(dbPhieu.TiemKiem(edtTK.getText().toString()).get(position)) != 0) {
                                Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                                tk();
                            } else {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainPhieu.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dbPhieu.Xoa(dbPhieu.LayDL().get(position)) != 0) {
                                Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                                LoadData();
                            } else {
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
        dbPhieu = new DBPhieu(this);
        adapterPhieu = new CustomAdapterPhieu(this, R.layout.list_phieu, dbPhieu.TiemKiem(edtTK.getText().toString()));
        lvPhieu.setAdapter(adapterPhieu);
    }


    private void LoadData() {
        dbPhieu = new DBPhieu(this);
        adapterPhieu = new CustomAdapterPhieu(this, R.layout.list_phieu, dbPhieu.LayDL());
        lvPhieu.setAdapter(adapterPhieu);
    }

    private void setControl() {
        btnTK = findViewById(R.id.btnTKPhieu);
        edtTK = findViewById(R.id.edtTKPhieu);
        btnThem = findViewById(R.id.btnThemPhieu);
        btnIndex = findViewById(R.id.btnBack);
        lvPhieu = findViewById(R.id.lvPhieu);
    }
}
