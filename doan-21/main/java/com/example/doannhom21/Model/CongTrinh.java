package com.example.doannhom21.Model;

public class CongTrinh {
    String maCT, tenCT , diaChiCT;

    public CongTrinh(String maCT, String tenCT, String diaChiCT) {
        this.maCT = maCT;
        this.tenCT = tenCT;
        this.diaChiCT = diaChiCT;
    }

    public String getMaCT() {
        return maCT;
    }

    public void setMaCT(String maCT) {
        this.maCT = maCT;
    }

    public String getTenCT() {
        return tenCT;
    }

    public void setTenCT(String tenCT) {
        this.tenCT = tenCT;
    }

    public String getDiaChiCT() {
        return diaChiCT;
    }

    public void setDiaChiCT(String diaCHiCT) {
        this.diaChiCT = diaCHiCT;
    }

    public CongTrinh(int id, String maCT, String tenCT, String diaCHiCT) {
        this.maCT = maCT;
        this.tenCT = tenCT;
        this.diaChiCT = diaCHiCT;
    }

    @Override
    public String toString() {
        return "CongTrinh{" +
                "maCT='" + maCT + '\'' +
                ", tenCT='" + tenCT + '\'' +
                ", diaCHiCT='" + diaChiCT + '\'' +
                '}';
    }
}
