package com.example.myapplication.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Model.CongTrinh;

import java.util.ArrayList;

public class DBCongTrinh {
    DBHelper dbHelper;

    public DBCongTrinh(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void Them(CongTrinh congTrinh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mact", congTrinh.getMaCT());
        values.put("tenct", congTrinh.getTenCT());
        values.put("diachict", congTrinh.getDiaChiCT());
        db.insert("CongTrinh", null, values);
    }

    public void Sua(CongTrinh congTrinh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mact", congTrinh.getMaCT());
        values.put("tenct", congTrinh.getTenCT());
        values.put("diachict", congTrinh.getDiaChiCT());
        db.update("CongTrinh", values, "mact = '" + congTrinh.getMaCT() + "'", null);
    }

    public void Xoa(CongTrinh congTrinh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql="Delete from CongTrinh where mact= '" + congTrinh.getMaCT() + "'";
        db.execSQL(sql);
    }

    public ArrayList<CongTrinh> LayDL() {
        ArrayList<CongTrinh> data = new ArrayList<>();
        String sql = "select * from CongTrinh";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                CongTrinh congTrinh = new CongTrinh();
                congTrinh.setMaCT(cursor.getString(0));
                congTrinh.setTenCT(cursor.getString(1));
                congTrinh.setDiaChiCT(cursor.getString(2));
                data.add(congTrinh);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
        }
        return data;
    }

    public ArrayList<CongTrinh> LayDL(String mact) {
        ArrayList<CongTrinh> data = new ArrayList<>();
        String sql = "select * from CongTrinh where mact ='" + mact + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                CongTrinh congTrinh = new CongTrinh();
                congTrinh.setMaCT(cursor.getString(0));
                congTrinh.setTenCT(cursor.getString(1));
                congTrinh.setDiaChiCT(cursor.getString(2));
                data.add(congTrinh);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
        }
        return data;
    }
}
