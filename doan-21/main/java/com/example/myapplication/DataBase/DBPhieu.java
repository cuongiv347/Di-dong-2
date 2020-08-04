package com.example.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Model.Phieu;

import java.util.ArrayList;

public class DBPhieu {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DBPhieu(Context context){dbHelper = new DBHelper(context);}

    public int Them(Phieu phieu){
        db = dbHelper.getWritableDatabase();
        String sql = "select * from Phieu where MaPhieu = '" + phieu.getMaPhieu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("MaPhieu",phieu.getMaPhieu());
            values.put("Ngay",phieu.getNgay());
            values.put("MaCT",phieu.getMaCongTrinh());
            db.insert("Phieu",null,values);
        }
        return cursor.getCount();
    }

    public  int Sua(Phieu phieu)
    {
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from Phieu where MaPhieu = '" + phieu.getMaPhieu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaPhieu",phieu.getMaPhieu());
            values.put("Ngay",phieu.getNgay());
            values.put("MaCT",phieu.getMaCongTrinh());
            db.update("Phieu",values,"MaPhieu ='"+phieu.getMaPhieu() +"'",null);
        }
        return cursor.getCount();
    }

    public  int Xoa(Phieu phieu)
    {
        db = dbHelper.getWritableDatabase();
        String sql = "select * from Phieu where MaPhieu = '" + phieu.getMaPhieu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaPhieu",phieu.getMaPhieu());
            values.put("Ngay",phieu.getNgay());
            values.put("MaCT",phieu.getMaCongTrinh());
            db.delete("Phieu","MaPhieu = '"+phieu.getMaPhieu()+"'",null);
        }
        return cursor.getCount();


    }

    public ArrayList<Phieu> TiemKiem(String txt){
        ArrayList<Phieu> data = new ArrayList<>();
        String sql="select * from Phieu where MaPhieu like '%"+txt+"%' order by ID desc";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                Phieu phieu = new Phieu();
                phieu.setMaPhieu(cursor.getString(1));
                phieu.setNgay(cursor.getString(2));
                phieu.setMaCongTrinh(cursor.getString(3));
                data.add(phieu);
            }
            while (cursor.moveToNext());
        }

        return  data;

    }

    public ArrayList<Phieu> LayDL(){
        ArrayList<Phieu> data = new ArrayList<>();
        String sql="select * from Phieu  order by ID desc";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                Phieu phieu = new Phieu();
                phieu.setMaPhieu(cursor.getString(1));
                phieu.setNgay(cursor.getString(2));
                phieu.setMaCongTrinh(cursor.getString(3));
                data.add(phieu);
            }
            while (cursor.moveToNext());
        }

        return  data;

    }
}
