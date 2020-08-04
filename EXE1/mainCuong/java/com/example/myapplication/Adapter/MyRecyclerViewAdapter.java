package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.GiaoDien.MainActivity;
import com.example.myapplication.Model.CardViewModel;
import com.example.myapplication.Model.CongTrinh;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private int layoutID;
    private Vector<CardViewModel> data;

    public MyRecyclerViewAdapter(int layoutID, Vector<CardViewModel> data) {
        this.layoutID = layoutID;
        this.data = data;
    }

    public MyRecyclerViewAdapter(MainActivity mainActivity, int card_view_layout, ArrayList<CongTrinh> dataCT) {
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tvMaCT;
        private TextView tvTenCT;
        private TextView tvDiaChiCT;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tvMaCT = (TextView) itemView.findViewById(R.id.tvMaCT);
            tvTenCT = (TextView) itemView.findViewById(R.id.tvTenCT);
            tvDiaChiCT = (TextView) itemView.findViewById(R.id.tvDiaChiCT);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        CardView viewItem = (CardView) inflater.inflate(layoutID, viewGroup, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        CardViewModel cardViewModel = data.get(i);
        Drawable drawable = viewHolder.imageView.getResources().getDrawable(cardViewModel.getImageResourceId());
        viewHolder.imageView.setImageDrawable(drawable);
        viewHolder.tvMaCT.setText(cardViewModel.getMaCT());
        viewHolder.tvTenCT.setText(cardViewModel.getTenCT());
        viewHolder.tvDiaChiCT.setText(cardViewModel.getDiaChiCT());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }



}

