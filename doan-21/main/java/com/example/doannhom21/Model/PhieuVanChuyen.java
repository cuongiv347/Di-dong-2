package com.example.doannhom21.Model;

public class PhieuVanChuyen {
    String maPhieu , ngay , maCongTrinh;

    public PhieuVanChuyen(int id, String maPhieu, String ngay, String maCongTrinh) {
        this.maPhieu = maPhieu;
        this.ngay = ngay;
        this.maCongTrinh = maCongTrinh;
    }

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
        return "PhieuVanChuyen{" +
                "maPhieu='" + maPhieu + '\'' +
                ", ngay='" + ngay + '\'' +
                ", maCongTrinh='" + maCongTrinh + '\'' +
                '}';
    }
}
