package com.example.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Model.CongTrinh;

import java.util.ArrayList;

public class DBCongTrinh {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DBCongTrinh(Context context){
        dbHelper = new DBHelper(context);
    }

    public int Them(CongTrinh congTrinh){
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from CongTrinh where MaCT = '" + congTrinh.getMaCongTrinh()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("MaCT",congTrinh.getMaCongTrinh());
            values.put("TenCT",congTrinh.getTenCongTrinh());
            values.put("DiaChiCT",congTrinh.getDiaChiCongTrinh());
            db.insert("CongTrinh",null,values);
        }
        return cursor.getCount();
    }


    public  int Sua(CongTrinh congTrinh)
    {
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from CongTrinh where MaCT = '" + congTrinh.getMaCongTrinh()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaCT",congTrinh.getMaCongTrinh());
            values.put("TenCT",congTrinh.getTenCongTrinh());
            values.put("DiaChiCT",congTrinh.getDiaChiCongTrinh());
            db.update("CongTrinh",values,"MaCT ='"+congTrinh.getMaCongTrinh() +"'",null);
        }
        return cursor.getCount();
    }

    public  int Xoa(CongTrinh congTrinh)
    {
        db = dbHelper.getWritableDatabase();
        String sql = "select * from CongTrinh where MaCT = '" + congTrinh.getMaCongTrinh()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaCT",congTrinh.getMaCongTrinh());
            values.put("TenCT",congTrinh.getTenCongTrinh());
            values.put("DiaChiCT",congTrinh.getDiaChiCongTrinh());
            db.delete("CongTrinh","MaCT = '"+congTrinh.getMaCongTrinh()+"'",null);
        }
        return cursor.getCount();


    }

    public ArrayList<CongTrinh> TimKiem(String txt){
        ArrayList<CongTrinh> data = new ArrayList<>();
        String sql="select * from CongTrinh where MaCT like '%"+txt+"%' order by ID desc";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                CongTrinh congTrinh = new CongTrinh();
                congTrinh.setMaCongTrinh(cursor.getString(1));
                congTrinh.setTenCongTrinh(cursor.getString(2));
                congTrinh.setDiaChiCongTrinh(cursor.getString(3));
                data.add(congTrinh);
            }
            while (cursor.moveToNext());
        }

        return  data;

    }


    public ArrayList<CongTrinh> LayDL(){
        ArrayList<CongTrinh> data = new ArrayList<>();
        String sql="select * from CongTrinh order by ID desc";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                CongTrinh congTrinh = new CongTrinh();
                congTrinh.setMaCongTrinh(cursor.getString(1));
                congTrinh.setTenCongTrinh(cursor.getString(2));
                congTrinh.setDiaChiCongTrinh(cursor.getString(3));
                data.add(congTrinh);
            }
            while (cursor.moveToNext());
        }

        return  data;

    }
}
