package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.ChiTietPhieu;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CustomAdapterChiTietPhieu extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ChiTietPhieu> data;


    public CustomAdapterChiTietPhieu(Context context, int resource, ArrayList<ChiTietPhieu> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource,null);

        ImageView imgChiTietPhieu = view.findViewById(R.id.imgChiTietPhieu);
        TextView tvSoPhieuCTP = view.findViewById(R.id.tvSoPhieuCTP);
        TextView tvcuLyCTP = view.findViewById(R.id.tvcuLyCTP);
        TextView tvMaPhieu = view.findViewById(R.id.tvMaPhieu_CTP);
        TextView tvMaVatTu_CTP = view.findViewById(R.id.tvMaVatTu_CTP);
        TextView tvSoLuongCTP = view.findViewById(R.id.tvSoLuongCTP);

        ChiTietPhieu cnvpp = data.get(position);
        imgChiTietPhieu.setImageResource(R.drawable.iconchitiet);
        tvSoPhieuCTP.setText(cnvpp.getSoPhieu());
        tvcuLyCTP.setText(cnvpp.getCuLy());
        tvMaPhieu.setText(cnvpp.getMaPhieu());
        tvMaVatTu_CTP.setText(cnvpp.getMaVatTu());
        tvSoLuongCTP.setText(cnvpp.getSoLuong());

        return view;
    }
}
