package com.example.myapplication.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "QLVanChuyen", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlVatTu =
                "create table VatTu(ID INTEGER PRIMARY KEY AUTOINCREMENT, MaVT text, TenVT text, DVT text, GiaVC text)";
        String sqlCongTrinh =
                "create table CongTrinh(ID INTEGER PRIMARY KEY AUTOINCREMENT, MaCT text, TenCT text, DiaChiCT text)";
        String sqlPhieu =
                "create table Phieu(ID INTEGER PRIMARY KEY AUTOINCREMENT, MaPhieu text, Ngay text, MaCT text)";
        String sqlChiTietPhieu =
                "create table ChiTietPhieu(ID INTEGER PRIMARY KEY AUTOINCREMENT, SoPhieu text, CuLy text, MaPhieu text, MaVT text, SoLuong text)";
        db.execSQL(sqlVatTu);
        db.execSQL(sqlCongTrinh);
        db.execSQL(sqlPhieu);
        db.execSQL(sqlChiTietPhieu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
