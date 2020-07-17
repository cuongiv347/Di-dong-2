package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.GiaoDien.MainDetailCT;
import com.example.myapplication.GiaoDien.MainGridView;
import com.example.myapplication.Model.CongTrinh;
import com.example.myapplication.R;
import java.util.ArrayList;
import java.util.Locale;

public class CustomApdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<CongTrinh> data;
    ArrayList<CongTrinh> data_DS;

    public CustomApdapter(Context context, int resource, ArrayList<CongTrinh> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<CongTrinh>();
        this.data_DS.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    static class Holder {
        TextView tvMaCT;
        TextView tvTenCT;
        TextView tvDiaChiCT;
        ImageView imgDetailCT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgDetailCT = view.findViewById(R.id.imgDetail);
            holder.tvMaCT = view.findViewById(R.id.tvMaCT);
            holder.tvTenCT = view.findViewById(R.id.tvTenCT);
            holder.tvDiaChiCT = view.findViewById(R.id.tvDiaChiCT);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final CongTrinh congTrinh = data.get(position);
        holder.tvMaCT.setText(congTrinh.getMaCT());
        holder.tvTenCT.setText(congTrinh.getTenCT());
        holder.tvDiaChiCT.setText(congTrinh.getDiaChiCT());
        holder.imgDetailCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainDetailCT.class);
                Bundle bundle = new Bundle();
                bundle.putString("mact", congTrinh.getMaCT());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(data_DS);
        } else {
            for (CongTrinh model : data_DS) {
                if (model.getMaCT().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
