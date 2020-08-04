package com.example.myapplication.Model;

public class Phieu {
    private String maPhieu;
    private String ngay;
    private String maCongTrinh;

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getMaCongTrinh() {
        return maCongTrinh;
    }

    public void setMaCongTrinh(String maCongTrinh) {
        this.maCongTrinh = maCongTrinh;
    }


    @Override
    public String toString() {
        return "Phieu{" +
                "maPhieu='" + maPhieu + '\'' +
                ", ngay='" + ngay + '\'' +
                ", maCongTrinh='" + maCongTrinh + '\'' +
                '}';
    }
}
