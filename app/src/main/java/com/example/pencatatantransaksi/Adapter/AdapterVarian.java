package com.example.pencatatantransaksi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pencatatantransaksi.Model.ModelBarang;
import com.example.pencatatantransaksi.Model.ModelBarangKeluar;
import com.example.pencatatantransaksi.Model.ModelSatuan;
import com.example.pencatatantransaksi.R;

import java.util.ArrayList;
import java.util.List;

import edu.counterview.CounterView;

public class AdapterVarian extends RecyclerView.Adapter<AdapterVarian.ProductViewHolder> {
    private Context mCtx;
    private List<ModelBarang> modelBarangs;

    public AdapterVarian(Context activity, List<ModelBarang> item) {
        this.mCtx = activity;
        this.modelBarangs = item;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_varian, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ModelBarang modelBarang = modelBarangs.get(position);

        //loading the image
        holder.option.setText(modelBarang.getVarian());
    }



    @Override
    public int getItemCount() {
        return modelBarangs.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        CheckBox option;
        CounterView jumlah;

        public ProductViewHolder(View itemView) {
            super(itemView);

            option = itemView.findViewById(R.id.optionVarian);
            jumlah = itemView.findViewById(R.id.cv);
        }
    }
}
