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

import com.example.myapplication.Model.VatTu;
import com.example.myapplication.Adapter.CustomAdapterVatTu;
import com.example.myapplication.R;
import com.example.myapplication.DataBase.DBVatTu;
import com.example.myapplication.GiaoDienAdd.MainThemVatTu;

public class MainVatTu extends AppCompatActivity {

    boolean check = false;
    DBVatTu dbVatTu;
    CustomAdapterVatTu adapterVatTu;
    Button btnThem, btnIndex, btnTK;
    EditText edtTK;
    ListView lvVatTu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vattu);

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
                Intent intent = new Intent(getApplicationContext(), MainThemVatTu.class);
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
                Tk();
                check = true;
            }
        });


        lvVatTu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (check) {
                    VatTu vatTu = dbVatTu.TiemKiem(edtTK.getText().toString()).get(position);
                    String sp = vatTu.getMaVatTu() + "-" + vatTu.getTenVatTu()
                            + "-" + vatTu.getDvTinh() + "-" + vatTu.getGiaVanChuyen();
                    Intent intent = new Intent(MainVatTu.this, MainThemVatTu.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("index", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    VatTu vatTu = dbVatTu.LayDL().get(position);
                    String sp = vatTu.getMaVatTu() + "-" + vatTu.getTenVatTu()
                            + "-" + vatTu.getDvTinh() + "-" + vatTu.getGiaVanChuyen();
                    Intent intent = new Intent(MainVatTu.this, MainThemVatTu.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("index", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }
        });

        lvVatTu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (check) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainVatTu.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dbVatTu.Xoa(dbVatTu.TiemKiem(edtTK.getText().toString()).get(position)) != 0) {
                                Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                                Tk();
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
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainVatTu.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dbVatTu.Xoa(dbVatTu.LayDL().get(position)) != 0) {
                                Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                                LoadData();
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

    private void Tk() {
        dbVatTu = new DBVatTu(this);
        adapterVatTu = new CustomAdapterVatTu(this, R.layout.list_vattu, dbVatTu.TiemKiem(edtTK.getText().toString()));
        lvVatTu.setAdapter(adapterVatTu);
    }


    private void LoadData() {
        dbVatTu = new DBVatTu(this);
        adapterVatTu = new CustomAdapterVatTu(this, R.layout.list_vattu, dbVatTu.LayDL());
        lvVatTu.setAdapter(adapterVatTu);
    }

    private void setControl() {
        btnThem = findViewById(R.id.btnThemVatTu);
        btnIndex = findViewById(R.id.btnBack);
        lvVatTu = findViewById(R.id.lvVatTu);
        btnTK = findViewById(R.id.btnTKVatTu);
        edtTK = findViewById(R.id.edtTKVatTu);
    }
}
