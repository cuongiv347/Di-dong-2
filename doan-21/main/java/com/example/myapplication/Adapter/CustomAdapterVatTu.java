package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.VatTu;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CustomAdapterVatTu extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<VatTu> data;


    public CustomAdapterVatTu(Context context, int resource, ArrayList<VatTu> data) {
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

        ImageView imgVatTu = view.findViewById(R.id.imgVatTu);
        TextView tvMaVatTu = view.findViewById(R.id.tvMaVatTu);
        TextView tvTenVatTu = view.findViewById(R.id.tvTenVatTu);
        TextView tvDVTinh = view.findViewById(R.id.tvDVTinh);
        TextView tvGiaVC = view.findViewById(R.id.tvGiaVC);

        VatTu vpp = data.get(position);
        imgVatTu.setImageResource(R.drawable.iconvattu);
        tvDVTinh.setText(vpp.getDvTinh());
        tvGiaVC.setText(vpp.getGiaVanChuyen());
        tvMaVatTu.setText(vpp.getMaVatTu());
        tvTenVatTu.setText(vpp.getTenVatTu());

        return view;
    }
}
