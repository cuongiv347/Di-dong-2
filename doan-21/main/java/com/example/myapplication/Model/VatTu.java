package com.example.myapplication.Model;

public class VatTu {
    private String maVatTu;
    private String tenVatTu;
    private String dvTinh;
    private String giaVanChuyen;

    public String getMaVatTu() {
        return maVatTu;
    }

    public void setMaVatTu(String maVatTu) {
        this.maVatTu = maVatTu;
    }

    public String getTenVatTu() {
        return tenVatTu;
    }

    public void setTenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
    }

    public String getDvTinh() {
        return dvTinh;
    }

    public void setDvTinh(String dvTinh) {
        this.dvTinh = dvTinh;
    }

    public String getGiaVanChuyen() {
        return giaVanChuyen;
    }

    public void setGiaVanChuyen(String giaVanChuyen) {
        this.giaVanChuyen = giaVanChuyen;
    }

    @Override
    public String toString() {
        return "VatTu{" +
                "maVatTu='" + maVatTu + '\'' +
                ", tenVatTu='" + tenVatTu + '\'' +
                ", dvTinh='" + dvTinh + '\'' +
                ", giaVanChuyen='" + giaVanChuyen + '\'' +
                '}';
    }
}
