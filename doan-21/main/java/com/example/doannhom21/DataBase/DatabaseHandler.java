package com.example.doannhom21.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.doannhom21.TableDataBase.Table_ChiTietPhieuVanChuyen;
import com.example.doannhom21.TableDataBase.Table_CongTrinh;
import com.example.doannhom21.TableDataBase.Table_PhieuVanChuyen;
import com.example.doannhom21.TableDataBase.Table_VatTu;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context) {
        super(context, Table_PhieuVanChuyen.DB_NAME,null, Table_PhieuVanChuyen.VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        //Tạo TABLE Công trình
        String sqlCongTrinh="CREATE TABLE "+ Table_CongTrinh.TABLE_NAME+"("
                +Table_CongTrinh.KEY_ID +" INTEGER PRIMARY KEY,"
                +Table_CongTrinh.KEY_MACONGTRINH + " TEXT,"
                +Table_CongTrinh.KEY_TENCONGTRINH + " TEXT,"
                +Table_CongTrinh.KEY_DIACHICONGTRINH+ " TEXT);";
        //Tạo TABLE Vật tư
        String sqlVatTu="CREATE TABLE "+ Table_VatTu.TABLE_NAME + " ("
                +Table_VatTu.KEY_ID + " INTEGER PRIMARY KEY,"
                +Table_VatTu.KEY_MAVATTU + " TEXT,"
                +Table_VatTu.KEY_TENVATTU + " TEXT,"
                +Table_VatTu.KEY_DONVITINH + " TEXT,"
                +Table_VatTu.KEY_GIAVANCHUYEN + " INTEGER);";
        //Tạo TABLE Phiếu vận chuyển
        String sqlChiTietPhieuVanChuyen="CREATE TABLE "+ Table_PhieuVanChuyen.TABLE_NAME+" ( "
                +Table_PhieuVanChuyen.KEY_ID+" INTEGER PRIMARY KEY ,"
                +Table_PhieuVanChuyen.KEY_MAPHIEU+" TEXT,"
                +Table_PhieuVanChuyen.KEY_NGAY+" TEXT,"
                +Table_PhieuVanChuyen.KEY_MACONGTRINH+" TEXT);";
        //Tạo TABLE Chi tiết phiếu vận chuyển
        String sqlPhieuVanChuyen="CREATE TABLE "+ Table_ChiTietPhieuVanChuyen.TABLE_NAME +"("
                +Table_ChiTietPhieuVanChuyen.KEY_ID + " INTEGER PRIMARY KEY,"
                +Table_ChiTietPhieuVanChuyen.KEY_MAPHIEU + " TEXT,"
                +Table_ChiTietPhieuVanChuyen.KEY_MAVATTU + " TEXT,"
                +Table_ChiTietPhieuVanChuyen.KEY_SOLUONG+ " INTEGER,"
                +Table_ChiTietPhieuVanChuyen.KEY_CULY + " INTEGER);";

        db.execSQL(sqlCongTrinh);
        db.execSQL(sqlVatTu);
        db.execSQL(sqlChiTietPhieuVanChuyen);
        db.execSQL(sqlPhieuVanChuyen);
        //dataAvailable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
