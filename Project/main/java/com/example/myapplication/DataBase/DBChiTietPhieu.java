package com.example.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.myapplication.Model.ChiTietPhieu;

import java.util.ArrayList;

public class DBChiTietPhieu {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DBChiTietPhieu(Context context){dbHelper = new DBHelper(context);}

    public int Them(ChiTietPhieu chiTietPhieu){
        db = dbHelper.getWritableDatabase();
        String sql = "select * from ChiTietPhieu where SoPhieu = '" + chiTietPhieu.getSoPhieu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("SoPhieu",chiTietPhieu.getSoPhieu());
            values.put("CuLy",chiTietPhieu.getCuLy());
            values.put("MaPhieu",chiTietPhieu.getMaPhieu());
            values.put("MaVT",chiTietPhieu.getMaVatTu());
            values.put("SoLuong",chiTietPhieu.getSoLuong());
            db.insert("ChiTietPhieu",null,values);
        }
        return cursor.getCount();
    }


    public  int Sua(ChiTietPhieu chiTietPhieu)
    {
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from ChiTietPhieu where SoPhieu = '" + chiTietPhieu.getSoPhieu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("SoPhieu",chiTietPhieu.getSoPhieu());
            values.put("CuLy",chiTietPhieu.getCuLy());
            values.put("MaPhieu",chiTietPhieu.getMaPhieu());
            values.put("MaVT",chiTietPhieu.getMaVatTu());
            values.put("SoLuong",chiTietPhieu.getSoLuong());
            db.update("ChiTietPhieu",values,"SoPhieu ='"+chiTietPhieu.getSoPhieu() +"'",null);
        }
        return cursor.getCount();
    }

    public  int Xoa(ChiTietPhieu chiTietPhieu)
    {
        db = dbHelper.getWritableDatabase();
        String sql = "select * from ChiTietPhieu where SoPhieu = '" + chiTietPhieu.getSoPhieu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("SoPhieu",chiTietPhieu.getSoPhieu());
            values.put("CuLy",chiTietPhieu.getCuLy());
            values.put("MaPhieu",chiTietPhieu.getMaPhieu());
            values.put("MaVT",chiTietPhieu.getMaVatTu());
            values.put("SoLuong",chiTietPhieu.getSoLuong());
            db.delete("ChiTietPhieu","SoPhieu = '"+chiTietPhieu.getSoPhieu()+"'",null);
        }
        return cursor.getCount();


    }

    public ArrayList<ChiTietPhieu> TiemKiem(String txt){
        ArrayList<ChiTietPhieu> data = new ArrayList<>();
        String sql="select * from ChiTietPhieu where SoPhieu like '%"+txt+"%' order by ID desc";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                ChiTietPhieu chiTietPhieu = new ChiTietPhieu();
                chiTietPhieu.setSoPhieu(cursor.getString(1));
                chiTietPhieu.setCuLy(cursor.getString(2));
                chiTietPhieu.setMaPhieu(cursor.getString(3));
                chiTietPhieu.setMaVatTu(cursor.getString(4));
                chiTietPhieu.setSoLuong(cursor.getString(5));
                data.add(chiTietPhieu);
            }
            while (cursor.moveToNext());
        }

        return  data;

    }

    public ArrayList<ChiTietPhieu> LayDL(){
        ArrayList<ChiTietPhieu> data = new ArrayList<>();
        String sql="select * from ChiTietPhieu  order by ID desc";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                ChiTietPhieu chiTietPhieu = new ChiTietPhieu();
                chiTietPhieu.setSoPhieu(cursor.getString(1));
                chiTietPhieu.setCuLy(cursor.getString(2));
                chiTietPhieu.setMaPhieu(cursor.getString(3));
                chiTietPhieu.setMaVatTu(cursor.getString(4));
                chiTietPhieu.setSoLuong(cursor.getString(5));
                data.add(chiTietPhieu);
            }
            while (cursor.moveToNext());
        }

        return  data;

    }
}
