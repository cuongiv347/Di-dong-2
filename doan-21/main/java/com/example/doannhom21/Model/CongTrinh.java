package com.example.doannhom21.Model;

public class CongTrinh {
    String maCT, tenCT , diaCHiCT;

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

    public String getDiaCHiCT() {
        return diaCHiCT;
    }

    public void setDiaCHiCT(String diaCHiCT) {
        this.diaCHiCT = diaCHiCT;
    }

    public CongTrinh(String maCT, String tenCT, String diaCHiCT) {
        this.maCT = maCT;
        this.tenCT = tenCT;
        this.diaCHiCT = diaCHiCT;
    }

    @Override
    public String toString() {
        return "CongTrinh{" +
                "maCT='" + maCT + '\'' +
                ", tenCT='" + tenCT + '\'' +
                ", diaCHiCT='" + diaCHiCT + '\'' +
                '}';
    }
}
