package com.example.myapplication.Model;

public class CardViewModel {
    String maCT;
    String tenCT;

    public CardViewModel(String maCT, String tenCT, String diaChiCT, int imageResourceId) {
        this.maCT = maCT;
        this.tenCT = tenCT;
        this.diaChiCT = diaChiCT;
        this.imageResourceId = imageResourceId;
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

    public void setDiaChiCT(String diaChiCT) {
        this.diaChiCT = diaChiCT;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    String diaChiCT;
    int imageResourceId;


}
