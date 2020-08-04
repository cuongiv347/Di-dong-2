package com.example.myapplication.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.myapplication.Adapter.CustomApdapter;
import com.example.myapplication.DataBase.DBCongTrinh;
import com.example.myapplication.DataBase.DBHelper;
import com.example.myapplication.Model.CongTrinh;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MainGridView extends AppCompatActivity {
    EditText txtMaCT, txtTenCT, txtDiaChi;
    GridView gvDanhSach;
    ArrayList<CongTrinh> dataCT = new ArrayList<>();
    CustomApdapter apdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid_view);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();

       gvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               AlertDialog.Builder builder = new AlertDialog.Builder(MainGridView.this);
               builder.setTitle("Xóa");
               builder.setMessage("Bạn có muốn xóa không ?");
               builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int i) {
                       Toast.makeText(MainGridView.this , "jhsjajd" , Toast.LENGTH_LONG).show();
//                       CongTrinh congTrinh = getCongTrinh();
//                       DBCongTrinh dbCongTrinh = new DBCongTrinh(getApplicationContext());
//                       dbCongTrinh.Xoa(congTrinh);
                   }
               });
               builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int i) {
                       dialog.cancel();
                   }
               });
               AlertDialog dialog = builder.create();
               dialog.show();
               return false;
           }
       });
    }

//    private CongTrinh getCongTrinh() {
//        CongTrinh congTrinh = new CongTrinh();
//        congTrinh.setMaCT(txtMaCT.getText().toString());
//        congTrinh.setTenCT(txtTenCT.getText().toString());
//        congTrinh.setDiaChiCT(txtDiaChi.getText().toString());
//        return congTrinh;
//    }

    private void setEvent() {
        HienThiDL();
        Toast.makeText(MainGridView.this , "All" , Toast.LENGTH_LONG).show();
    }

    private void HienThiDL() {
        DBCongTrinh dbCongTrinh = new DBCongTrinh(this);
        dataCT = dbCongTrinh.LayDL();
        apdapter = new CustomApdapter(MainGridView.this, R.layout.listview_item, dataCT);
        gvDanhSach.setAdapter(apdapter);
    }

    private void setControl() {
        gvDanhSach = findViewById(R.id.gvDanhSach);
        txtMaCT = findViewById(R.id.txtMaCT);
        txtTenCT = findViewById(R.id.txtTenCT);
        txtDiaChi = findViewById(R.id.txtDiaChiCT);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                Intent intent = new Intent(MainGridView.this , MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

