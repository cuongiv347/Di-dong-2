package com.example.myapplication.Model;

public class CongTrinh {
    String maCT , tenCT , diaChiCT;

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

    public void setDiaChiCT(String diaChiCT) {
        this.diaChiCT = diaChiCT;
    }

    @Override
    public String toString() {
        return "CongTrinh{" +
                "maCT='" + maCT + '\'' +
                ", tenCT='" + tenCT + '\'' +
                ", diaChiCT='" + diaChiCT + '\'' +
                '}';
    }
}
