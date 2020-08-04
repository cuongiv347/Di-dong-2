package com.example.myapplication.Model;

public class ChiTietPhieu {
    private String soPhieu;
    private String cuLy;
    private String maPhieu;
    private String maVatTu;
    private String soLuong;

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getCuLy() {
        return cuLy;
    }

    public void setCuLy(String cuLy) {
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


    @Override
    public String toString() {
        return "ChiTietPhieu{" +
                "soPhieu='" + soPhieu + '\'' +
                ", ngay='" + cuLy + '\'' +
                ", maPhieu='" + maPhieu + '\'' +
                ", maVatTu='" + maVatTu + '\'' +
                ", soLuong='" + soLuong + '\'' +
                '}';
    }
}
