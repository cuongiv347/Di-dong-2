package com.example.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Model.VatTu;

import java.util.ArrayList;

public class DBVatTu {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DBVatTu(Context context){
        dbHelper = new DBHelper(context);
    }

    public int Them(VatTu vatTu){
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from VatTu where MaVT = '" + vatTu.getMaVatTu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("MaVT",vatTu.getMaVatTu());
            values.put("TenVT",vatTu.getTenVatTu());
            values.put("DVT",vatTu.getDvTinh());
            values.put("GiaVC",vatTu.getGiaVanChuyen());
            db.insert("VatTu",null,values);
        }
        return cursor.getCount();
    }

    public  int Sua(VatTu vatTu)
    {
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from VatTu where MaVT = '" + vatTu.getMaVatTu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaVT",vatTu.getMaVatTu());
            values.put("TenVT",vatTu.getTenVatTu());
            values.put("DVT",vatTu.getDvTinh());
            values.put("GiaVC",vatTu.getGiaVanChuyen());
            db.update("VatTu",values,"MaVT ='"+vatTu.getMaVatTu() +"'",null);
        }
        return cursor.getCount();
    }

    public  int Xoa(VatTu vatTu)
    {
        db = dbHelper.getWritableDatabase();
        String sql = "select * from VatTu where MaVT = '" + vatTu.getMaVatTu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaVT",vatTu.getMaVatTu());
            values.put("TenVT",vatTu.getTenVatTu());
            values.put("DVT",vatTu.getDvTinh());
            values.put("GiaVC",vatTu.getGiaVanChuyen());
            db.delete("VatTu","MaVT = '"+vatTu.getMaVatTu()+"'",null);
        }
        return cursor.getCount();
    }

    public ArrayList<VatTu> TiemKiem(String txt){
        ArrayList<VatTu> data = new ArrayList<>();
        String sql="select * from VatTu where MaVT like '%" + txt + "%' order by ID desc";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                VatTu vatTu = new VatTu();
                vatTu.setMaVatTu(cursor.getString(1));
                vatTu.setTenVatTu(cursor.getString(2));
                vatTu.setDvTinh(cursor.getString(3));
                vatTu.setGiaVanChuyen(cursor.getString(4));
                data.add(vatTu);
            }
            while (cursor.moveToNext());
        }
        return  data;
    }

    public ArrayList<VatTu> LayDL(){
        ArrayList<VatTu> data = new ArrayList<>();
        String sql="select * from VatTu order by ID desc";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                VatTu vatTu = new VatTu();
                vatTu.setMaVatTu(cursor.getString(1));
                vatTu.setTenVatTu(cursor.getString(2));
                vatTu.setDvTinh(cursor.getString(3));
                vatTu.setGiaVanChuyen(cursor.getString(4));
                data.add(vatTu);
            }
            while (cursor.moveToNext());
        }

        return  data;

    }
}
