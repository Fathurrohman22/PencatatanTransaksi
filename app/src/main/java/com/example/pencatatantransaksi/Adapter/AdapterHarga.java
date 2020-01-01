package com.example.pencatatantransaksi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pencatatantransaksi.Model.ModelBarang;
import com.example.pencatatantransaksi.Model.ModelInputHarga;
import com.example.pencatatantransaksi.R;

import java.util.List;

public class AdapterHarga extends RecyclerView.Adapter<AdapterHarga.ProductViewHolder> {

    private Context mCtx;
    private List<ModelInputHarga> modelInputHargas;

    public AdapterHarga(Context mCtx, List<ModelInputHarga> modelInputHargas) {
        this.mCtx = mCtx;
        this.modelInputHargas = modelInputHargas;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_harga, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterHarga.ProductViewHolder holder, int position) {
        ModelInputHarga modelInputHarga = modelInputHargas.get(position);

        //loading the image

        holder.kategori.setText(modelInputHarga.getNama_kategori());
        holder.satuan.setText(modelInputHarga.getNama_satuan());
        holder.harga1pcs.setText(modelInputHarga.getHarga1pcs());
        holder.harga10pcs.setText(modelInputHarga.getHarga10pcs());
        holder.harga50pcs.setText(modelInputHarga.getHarga50pcs());
        holder.harga100pcs.setText(modelInputHarga.getHarga100pcs());
    }

    @Override
    public int getItemCount() {
        return modelInputHargas.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView kategori, satuan, harga1pcs, harga10pcs, harga50pcs, harga100pcs;

        public ProductViewHolder(View itemView) {
            super(itemView);

            kategori = itemView.findViewById(R.id.nama_kategori);
            satuan = itemView.findViewById(R.id.nama_satuan);
            harga1pcs = itemView.findViewById(R.id.harga_barang_1pcs);
            harga10pcs = itemView.findViewById(R.id.harga_barang_10pcs);
            harga50pcs = itemView.findViewById(R.id.harga_barang_50pcs);
            harga100pcs = itemView.findViewById(R.id.harga_barang_100pcs);
        }
    }

}

