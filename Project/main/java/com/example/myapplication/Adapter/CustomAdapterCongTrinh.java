package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.CongTrinh;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CustomAdapterCongTrinh extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<CongTrinh> data;


    public CustomAdapterCongTrinh(Context context, int resource, ArrayList<CongTrinh> data) {
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

        ImageView imgCT = view.findViewById(R.id.imgCT);
        TextView tvMaCT = view.findViewById(R.id.tvMaCT);
        TextView tvTenCT = view.findViewById(R.id.tvTenCT);
        TextView tvDiaChiCT = view.findViewById(R.id.tvDiaChiCT);
        CongTrinh congTrinh = data.get(position);
        imgCT.setImageResource(R.drawable.iconcongtrinh);
        tvMaCT.setText(congTrinh.getMaCongTrinh());
        tvTenCT.setText(congTrinh.getTenCongTrinh());
        tvDiaChiCT.setText(congTrinh.getDiaChiCongTrinh());

        return view;
    }
}
