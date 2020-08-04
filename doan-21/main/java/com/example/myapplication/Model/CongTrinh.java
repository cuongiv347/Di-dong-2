package com.example.myapplication.Model;

public class CongTrinh {
    private String maCongTrinh;
    private String tenCongTrinh;
    private String diaChiCongTrinh;

    public String getMaCongTrinh() {
        return maCongTrinh;
    }

    public void setMaCongTrinh(String maCongTrinh) {
        this.maCongTrinh = maCongTrinh;
    }

    public String getTenCongTrinh() {
        return tenCongTrinh;
    }

    public void setTenCongTrinh(String tenCongTrinh) {
        this.tenCongTrinh = tenCongTrinh;
    }

    public String getDiaChiCongTrinh() {
        return diaChiCongTrinh;
    }

    public void setDiaChiCongTrinh(String diaChiCongTrinh) {
        this.diaChiCongTrinh = diaChiCongTrinh;
    }

    @Override
    public String toString() {
        return "CongTrinh{" +
                "maCongTrinh='" + maCongTrinh + '\'' +
                ", tenCongTrinh='" + tenCongTrinh + '\'' +
                ", diaChiCongTrinh='" + diaChiCongTrinh + '\'' +
                '}';
    }
}
