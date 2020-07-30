package com.example.doannhom21.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doannhom21.Model.VatTu;
import com.example.doannhom21.R;

import java.util.ArrayList;

public class CustomAdapterVatTu extends RecyclerView.Adapter<CustomAdapterVatTu.MyViewHolder> {
    private int layoutID;
    private ArrayList<VatTu> data;
    private Listener listener;
    public static interface Listener{
        public void onClick(int position);
    }

    public CustomAdapterVatTu(int layoutID, ArrayList<VatTu> data) {
        this.layoutID = layoutID;
        this.data = data;
    }

    public static class MyViewHolder<CardView extends View> extends RecyclerView.ViewHolder{
        private TextView info_maVT;
        private TextView info_tenVT;
        private ImageView info_dvTinh;
        private TextView info_giaVanChuyen;
        private CardView cardView;

        public MyViewHolder(@NonNull CardView v) {
            super(v);
            info_maVT=itemView.findViewById(R.id.ivMaVatTu);
            info_tenVT=itemView.findViewById(R.id.ivTenVatTu);
            info_dvTinh=itemView.findViewById(R.id.ivDVTinh);
            info_giaVanChuyen=itemView.findViewById(R.id.ivGiaVanChuyen);
            cardView=v;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        CardView cardView= (CardView) inflater.inflate(layoutID,viewGroup,false);
        return new MyViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final CardView cardView= (CardView) myViewHolder.cardView;
        VatTu vatTu=data.get(i);
        if(vatTu.getDvTinh().equals("Việt Nam"))
            myViewHolder.info_dvTinh.setImageResource(R.drawable.anhmattroi);
        else if (vatTu.getDvTinh().equals("Mỹ"))
            myViewHolder.info_dvTinh.setImageResource(R.drawable.ic_baseline_done_all_24);
        else
            myViewHolder.info_dvTinh.setImageResource(R.drawable.ic_baseline_library_add_check_24);

        myViewHolder.info_maVT.setText("Mã Vat tu:"+vatTu.getMaVatTu());
        myViewHolder.info_tenVT.setText("Tên  vat tu:"+vatTu.getTenVatTu());
        myViewHolder.info_giaVanChuyen.setText("gia vat tu:"+vatTu.getGiaVanChuyen());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null)
                    listener.onClick(myViewHolder.getAdapterPosition());
            }
        });

    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
