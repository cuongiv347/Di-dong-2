package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.Phieu;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CustomAdapterPhieu extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Phieu> data;


    public CustomAdapterPhieu(Context context, int resource, ArrayList<Phieu> data) {
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

        ImageView imgPhieu = view.findViewById(R.id.imgPhieu);
        TextView tvMaPhieu = view.findViewById(R.id.tvMaPhieu);
        TextView tvNgay = view.findViewById(R.id.tvNgay);
        TextView tvMaCongTrinh_Phieu = view.findViewById(R.id.tvMaCongTrinh_Phieu);

        Phieu nv = data.get(position);
        imgPhieu.setImageResource(R.drawable.iconphieu);
        tvMaPhieu.setText(nv.getMaPhieu());
        tvNgay.setText(nv.getNgay());
        tvMaCongTrinh_Phieu.setText(nv.getMaCongTrinh());

        return view;
    }
}
