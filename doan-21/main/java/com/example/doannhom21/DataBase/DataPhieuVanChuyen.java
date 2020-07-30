package com.example.doannhom21.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doannhom21.Model.PhieuVanChuyen;
import com.example.doannhom21.TableDataBase.Table_PhieuVanChuyen;

import java.util.ArrayList;

public class DataPhieuVanChuyen {
    DatabaseHandler handler;

    public DataPhieuVanChuyen(Context context) {
        this.handler = new DatabaseHandler(context);
    }


    public void themPhieuVanChuyen(PhieuVanChuyen phieuVanChuyen)
    {
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table_PhieuVanChuyen.KEY_MAPHIEU,phieuVanChuyen.getMaPhieu()+String.format("%02d",getCountByType(phieuVanChuyen.getMaPhieu())+1));
        values.put(Table_PhieuVanChuyen.KEY_NGAY,phieuVanChuyen.getNgay());
        values.put(Table_PhieuVanChuyen.KEY_MACONGTRINH,phieuVanChuyen.getMaCongTrinh());

        db.insert(Table_PhieuVanChuyen.TABLE_NAME,null,values);
    }
    public ArrayList<PhieuVanChuyen> getAllPhieuVanChuyen(){
        SQLiteDatabase db=handler.getReadableDatabase();
        ArrayList<PhieuVanChuyen> phieuVanChuyens=new ArrayList<>();
        String sql="SELECT * from "+Table_PhieuVanChuyen.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String maPhieu=cursor.getString(1);
                String ngay=cursor.getString(2);
                String maCongTrinh=cursor.getString(3);
                phieuVanChuyens.add(new PhieuVanChuyen(id,maPhieu,ngay,maCongTrinh));
            }while (cursor.moveToNext());
        }
        return phieuVanChuyens;
    }
    //dem so san pham theo ma loai
    public int getCountByType(String maPhieu){
        String sql="SELECT COUNT(*) " +
                " from "+Table_PhieuVanChuyen.TABLE_NAME
                +" WHERE "+ Table_PhieuVanChuyen.KEY_MAPHIEU +" = '"+maPhieu+"' ";
        //SELECT COUNT(*)  from thietBi WHERE thietBi.maLoai="CS"
        SQLiteDatabase db=handler.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst())
            return cursor.getInt(0);
        return 0;
    }
    //dem tong san pham
    public int getCount(){
        String sql="SELECT * from "+Table_PhieuVanChuyen.TABLE_NAME;
        SQLiteDatabase db=handler.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        return cursor.getCount();
    }
    public void xoaPhieu(PhieuVanChuyen phieuVanChuyen){
        SQLiteDatabase db=handler.getWritableDatabase();
        db.delete(Table_PhieuVanChuyen.TABLE_NAME,Table_PhieuVanChuyen.KEY_MAPHIEU+" =?",new String[]{phieuVanChuyen.getMaPhieu()});
    }
    public int suaPhieu(PhieuVanChuyen phieuVanChuyen){
        SQLiteDatabase db=handler.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(Table_PhieuVanChuyen.KEY_NGAY,phieuVanChuyen.getNgay());
        values.put(Table_PhieuVanChuyen.KEY_MACONGTRINH,phieuVanChuyen.getMaCongTrinh());

        return db.update(Table_PhieuVanChuyen.TABLE_NAME,values,Table_PhieuVanChuyen.KEY_MAPHIEU+" =?",new String[]{phieuVanChuyen.getMaPhieu()});
    }
}
