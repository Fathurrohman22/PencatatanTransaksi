package com.example.pencatatantransaksi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pencatatantransaksi.Model.ModelKategori;
import com.example.pencatatantransaksi.R;

import java.util.List;

public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.ProductViewHolder> {


    private Context mCtx;
    private List<ModelKategori> modelKategoris;

    public AdapterKategori(Context mCtx, List<ModelKategori> modelKategoris) {
        this.mCtx = mCtx;
        this.modelKategoris = modelKategoris;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_kategori, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ModelKategori modelKategori = modelKategoris.get(position);

        //loading the image

        holder.nama.setText(modelKategori.getNama_kategori());
    }

    @Override
    public int getItemCount() {
        return modelKategoris.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView nama;
        ImageView option;

        public ProductViewHolder(View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.nama_kategori);
            option = itemView.findViewById(R.id.option);
        }
    }
}