package com.example.doannhom21.Model;

public class ChiTietPhieuVanChuyen {
    String maPhieu , maVatTu , soLuong , cuLy;


    public ChiTietPhieuVanChuyen(int id, String maPhieu, String maVatTu, String soLuong, String cuLy) {
        this.maPhieu = maPhieu;
        this.maVatTu = maVatTu;
        this.soLuong = soLuong;
        this.cuLy = cuLy;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaVatTu() {
        return maVatTu;
    }

    public void setMaVatTu(String maVatTu) {
        this.maVatTu = maVatTu;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getCuLy() {
        return cuLy;
    }

    public void setCuLy(String cuLy) {
        this.cuLy = cuLy;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuVanChuyen{" +
                "maPhieu='" + maPhieu + '\'' +
                ", maVatTu='" + maVatTu + '\'' +
                ", soLuong='" + soLuong + '\'' +
                ", cuLy='" + cuLy + '\'' +
                '}';
    }
}
