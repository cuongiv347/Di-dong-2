package com.example.doannhom21.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doannhom21.Model.CongTrinh;
import com.example.doannhom21.TableDataBase.Table_CongTrinh;

import java.util.ArrayList;

public class DataCongTrinh {
    DatabaseHandler handler;

    public DataCongTrinh(Context context) {
        this.handler = new DatabaseHandler(context);
    }

    public void themCongTrinh(CongTrinh congTrinh)
    {
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_CongTrinh
                .KEY_MACONGTRINH,congTrinh.getMaCT());
        values.put(Table_CongTrinh.KEY_TENCONGTRINH,congTrinh.getTenCT());
        values.put(Table_CongTrinh.KEY_DIACHICONGTRINH,congTrinh.getDiaChiCT());
        db.insert(Table_CongTrinh.TABLE_NAME,null,values);
        db.close();
    }
    public ArrayList<CongTrinh> getAllCongTrinh(){
        ArrayList<CongTrinh> congTrinhs=new ArrayList<>();
        SQLiteDatabase db=handler.getReadableDatabase();
        String sql="SELECT * from " + Table_CongTrinh.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor!=null && cursor.moveToFirst()) {
            do {
                String maCongTrinh = cursor.getString(1);
                String tenCongTrinh = cursor.getString(2);
                String diaChiCT= cursor.getString(3);
                CongTrinh congTrinh = new CongTrinh(maCongTrinh, tenCongTrinh,diaChiCT);
                congTrinhs.add(congTrinh);
            } while (cursor.moveToNext());
        }
        return congTrinhs;
    }
    public void xoaCongTrinh(CongTrinh congTrinh){
        SQLiteDatabase db=handler.getWritableDatabase();
        db.delete(Table_CongTrinh.TABLE_NAME,Table_CongTrinh.KEY_MACONGTRINH+"=?",new String[]{congTrinh.getMaCT()});
    }
    public int capNhatCongTrinh(CongTrinh congTrinh){
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_CongTrinh.KEY_MACONGTRINH,congTrinh.getMaCT());
        values.put(Table_CongTrinh.KEY_TENCONGTRINH,congTrinh.getTenCT());
        values.put(Table_CongTrinh.KEY_DIACHICONGTRINH,congTrinh.getDiaChiCT());
        return db.update(Table_CongTrinh.TABLE_NAME,values,Table_CongTrinh.KEY_MACONGTRINH+"=?",new String[]{congTrinh.getMaCT()});
    }
    public int getCountCongTrinh(){
        String sql="SELECT * from "+Table_CongTrinh.TABLE_NAME;
        SQLiteDatabase db=handler.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        return cursor.getCount();
    }
}
