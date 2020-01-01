package com.example.pencatatantransaksi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pencatatantransaksi.Model.ModelKategori;
import com.example.pencatatantransaksi.Model.ModelSatuan;
import com.example.pencatatantransaksi.R;

import java.util.List;

public class AdapterSatuan extends RecyclerView.Adapter<AdapterSatuan.ProductViewHolder> {
    private Context mCtxSat;
    private List<ModelSatuan> modelSatuans;
    private Activity activity;
    private LayoutInflater inflater;

    public AdapterSatuan(Context mCtxSat, List<ModelSatuan> modelSatuans) {
        this.mCtxSat = mCtxSat;
        this.modelSatuans = modelSatuans;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtxSat);
        View view = inflater.inflate(R.layout.item_satuan, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ModelSatuan modelSatuan = modelSatuans.get(position);

        holder.nama.setText(modelSatuan.getNama_satuan());
        holder.jumlah.setText(modelSatuan.getJumlah());
    }

    @Override
    public int getItemCount() {
        return modelSatuans.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView nama;
        TextView jumlah;
        ImageView option;

        public ProductViewHolder(View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.nama_satuan);
            jumlah = itemView.findViewById(R.id.jumlah_satuan);
            option = itemView.findViewById(R.id.option);
        }
    }

}
