package com.example.doannhom21.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doannhom21.Model.ChiTietPhieuVanChuyen;
import com.example.doannhom21.TableDataBase.Table_ChiTietPhieuVanChuyen;

import java.util.ArrayList;

public class DataChiTietPhieuVanChuyen {
    DatabaseHandler handler;

    public DataChiTietPhieuVanChuyen(Context context) {
        this.handler = new DatabaseHandler(context);
    }


    public void themThietbi(ChiTietPhieuVanChuyen chiTietPhieuVanChuyen)
    {
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_ChiTietPhieuVanChuyen.KEY_MAPHIEU,chiTietPhieuVanChuyen.getMaPhieu()+String.format("%02d",getCountByType(chiTietPhieuVanChuyen.getMaPhieu())+1));
        values.put(Table_ChiTietPhieuVanChuyen.KEY_MAVATTU,chiTietPhieuVanChuyen.getMaVatTu());
        values.put(Table_ChiTietPhieuVanChuyen.KEY_SOLUONG,chiTietPhieuVanChuyen.getSoLuong());
        values.put(Table_ChiTietPhieuVanChuyen.KEY_CULY,chiTietPhieuVanChuyen.getCuLy());

        db.insert(Table_ChiTietPhieuVanChuyen.TABLE_NAME,null,values);
    }
    public ArrayList<ChiTietPhieuVanChuyen> getAllChiTietPhieuVanChuyen(){
        SQLiteDatabase db=handler.getReadableDatabase();
        ArrayList<ChiTietPhieuVanChuyen> chiTietPhieuVanChuyens=new ArrayList<>();
        String sql="SELECT * from "+Table_ChiTietPhieuVanChuyen.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String maPhieu=cursor.getString(1);
                String maVatTu=cursor.getString(2);
                String soLuong=cursor.getString(3);
                String cuLy=cursor.getString(4);
                chiTietPhieuVanChuyens.add(new ChiTietPhieuVanChuyen(id,maPhieu,maVatTu,soLuong,cuLy));
            }while (cursor.moveToNext());
        }
        return chiTietPhieuVanChuyens;
    }
    //dem so san pham theo ma loai
    public int getCountByType(String maPhieu){
        String sql="SELECT COUNT(*) " +
                " from "+Table_ChiTietPhieuVanChuyen.TABLE_NAME
                +" WHERE "+ Table_ChiTietPhieuVanChuyen.KEY_MAPHIEU +" = '"+maPhieu+"' ";
        //SELECT COUNT(*)  from thietBi WHERE thietBi.maLoai="CS"
        SQLiteDatabase db=handler.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst())
            return cursor.getInt(0);
        return 0;
    }
    //dem tong san pham
    public int getCount(){
        String sql="SELECT * from "+Table_ChiTietPhieuVanChuyen.TABLE_NAME;
        SQLiteDatabase db=handler.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        return cursor.getCount();
    }
    public void xoaThietBi(ChiTietPhieuVanChuyen chiTietPhieuVanChuyen){
        SQLiteDatabase db=handler.getWritableDatabase();
        db.delete(Table_ChiTietPhieuVanChuyen.TABLE_NAME,Table_ChiTietPhieuVanChuyen.KEY_MAPHIEU+" =?",new String[]{chiTietPhieuVanChuyen.getMaPhieu()});
    }
    public int suaThietBi(ChiTietPhieuVanChuyen chiTietPhieuVanChuyen){
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(Table_ChiTietPhieuVanChuyen.KEY_MAVATTU,chiTietPhieuVanChuyen.getMaVatTu());
        values.put(Table_ChiTietPhieuVanChuyen.KEY_SOLUONG,chiTietPhieuVanChuyen.getSoLuong());
        values.put(Table_ChiTietPhieuVanChuyen.KEY_CULY,chiTietPhieuVanChuyen.getCuLy());

        return db.update(Table_ChiTietPhieuVanChuyen.TABLE_NAME,values,Table_ChiTietPhieuVanChuyen.KEY_MAPHIEU+" =?",new String[]{chiTietPhieuVanChuyen.getMaPhieu()});
    }
}
