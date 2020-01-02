package com.example.pencatatantransaksi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pencatatantransaksi.Helper.SeleksiBarangKeluarModel;
import com.example.pencatatantransaksi.Model.ModelBarang;
import com.example.pencatatantransaksi.Model.ModelKategori;
import com.example.pencatatantransaksi.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.ProductViewHolder> {

    private Context mCtx;
    private List<ModelBarang> modelBarangs;


    public AdapterBarang(Context mCtx, List<ModelBarang> modelBarangs) {
        this.mCtx = mCtx;
        this.modelBarangs = modelBarangs;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_barang, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ModelBarang modelBarang = modelBarangs.get(position);

        //loading the image

        holder.kategori.setText(modelBarang.getNama_kategori());
        holder.varian.setText(modelBarang.getVarian());
        holder.stok.setText(modelBarang.getStok());
        holder.satuan.setText(modelBarang.getNama_satuan());

    }

    @Override
    public int getItemCount() {
        return modelBarangs.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView kategori, varian, stok, satuan;

        public ProductViewHolder(View itemView) {
            super(itemView);

            kategori = itemView.findViewById(R.id.nama_kategori);
            varian = itemView.findViewById(R.id.nama_varian);
            stok = itemView.findViewById(R.id.stok);
            satuan = itemView.findViewById(R.id.nama_satuan);
        }
    }

}
