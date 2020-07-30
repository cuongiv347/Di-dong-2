package com.example.doannhom21.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doannhom21.Model.VatTu;
import com.example.doannhom21.TableDataBase.Table_VatTu;

import java.util.ArrayList;

public class DataVatTu {
    DatabaseHandler handler;

    public DataVatTu(Context context) {
        this.handler = new DatabaseHandler(context);
    }

    public void themVatTu(VatTu vatTu)
    {
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_VatTu.KEY_MAVATTU,vatTu.getMaVatTu());
        values.put(Table_VatTu.KEY_TENVATTU,vatTu.getTenVatTu());
        values.put(Table_VatTu.KEY_DONVITINH,vatTu.getDvTinh());
        values.put(Table_VatTu.KEY_GIAVANCHUYEN,vatTu.getGiaVanChuyen());
        db.insert(Table_VatTu.TABLE_NAME,null,values);
        db.close();
    }
    public ArrayList<VatTu> getAllVatTu(){
        ArrayList<VatTu> vatTus=new ArrayList<>();
        SQLiteDatabase db=handler.getReadableDatabase();
        String sql="SELECT * from " + Table_VatTu.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor!=null && cursor.moveToFirst()) {
            do {
                String maVatTu = cursor.getString(1);
                String tenVatTu = cursor.getString(2);
                String donViTinh = cursor.getString(3);
                String giaVanChuyen = cursor.getString(4);
                VatTu vatTu = new VatTu(0, maVatTu, tenVatTu,donViTinh,giaVanChuyen);
                vatTus.add(vatTu);
            } while (cursor.moveToNext());
        }
        return vatTus;
    }
    public void xoaVatTu(VatTu vatTu){
        SQLiteDatabase db=handler.getWritableDatabase();
        db.delete(Table_VatTu.TABLE_NAME,Table_VatTu.KEY_MAVATTU+"=?",new String[]{vatTu.getMaVatTu()});
    }
    public int capNhatVatTu(VatTu vatTu){
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_VatTu.KEY_MAVATTU,vatTu.getMaVatTu());
        values.put(Table_VatTu.KEY_TENVATTU,vatTu.getTenVatTu());
        values.put(Table_VatTu.KEY_DONVITINH,vatTu.getDvTinh());
        values.put(Table_VatTu.KEY_GIAVANCHUYEN,vatTu.getGiaVanChuyen());
        return db.update(Table_VatTu.TABLE_NAME,values,Table_VatTu.KEY_MAVATTU+"=?",new String[]{vatTu.getMaVatTu()});
    }
    public int getCountVatTu(){
        String sql="SELECT * from "+Table_VatTu.TABLE_NAME;
        SQLiteDatabase db=handler.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        return cursor.getCount();
    }
}
