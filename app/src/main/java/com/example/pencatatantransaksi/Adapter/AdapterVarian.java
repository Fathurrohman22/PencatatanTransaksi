package com.example.pencatatantransaksi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.NumberFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Locale;

import android.widget.Filter;
import android.widget.TextView;

import com.example.pencatatantransaksi.Model.ModelBarang;
import com.example.pencatatantransaksi.Model.ModelHarga;
import com.example.pencatatantransaksi.R;

public class AdapterVarian extends RecyclerView.Adapter<AdapterVarian.ViewHolder> implements Filterable {

    private static final String TAG = AdapterVarian.class.getSimpleName();
    private Context context;
    private ArrayList<ModelBarang> dataset;
    private ArrayList<ModelBarang> datasetFilter;
    private static ClickListener clickListener;
    private CustomFilter filter;
    private TextView tvTotal, tvTotalHarga;
    private int[] seleksi;
    private ModelHarga modelHarga;

    public AdapterVarian(Context context, ArrayList<ModelBarang> dataset, TextView tvTotal, TextView tvTotalHarga, ModelHarga modelHarga) {
        this.context = context;
        this.dataset = dataset;
        this.tvTotal = tvTotal;
        this.datasetFilter = dataset;
        this.tvTotalHarga = tvTotalHarga;
        this.modelHarga = modelHarga;
        seleksi = new int[dataset.size()];
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        AdapterVarian.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_varian, parent, false);
        return new ViewHolder(view);
    }

    public interface ClickListener {
        void onItemClick(int position, View view);

        void onItemLongClick(int position, View view);
    }

    private int total = 0;
    private int hartot = 0;

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.btnMin.setEnabled(false);
        holder.btnPlus.setEnabled(false);

        final ModelBarang modelBarang = dataset.get(position);
        seleksi[position] = 0;

        //loading the image
        holder.option.setText(modelBarang.getVarian());
        holder.option.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    holder.btnMin.setEnabled(true);
                    holder.btnPlus.setEnabled(true);

                    holder.btnPlus.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(View v) {
                            seleksi[position] = seleksi[position] + 1;
                            holder.tvJml.setText("" + seleksi[position]);
                            setTotal();
                        }
                    });

                    holder.btnMin.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(View v) {
                            seleksi[position] = seleksi[position] - 1;
                            if (seleksi[position] < 1) {
                                seleksi[position] = 1;
                                holder.btnMin.setEnabled(false);
                            } else {
                                holder.btnMin.setEnabled(true);
                            }
                            holder.tvJml.setText("" + seleksi[position]);
                            setTotal();
                        }
                    });
                } else {
                    holder.btnMin.setEnabled(false);
                    holder.btnPlus.setEnabled(false);
                    seleksi[position] = 0;
                    holder.tvJml.setText("" + seleksi[position]);
                    setTotal();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setTotal() {
        total = 0;
        for (int i : seleksi) {
            total = total + i;
        }
        tvTotal.setText("" + total);
        setHarga(total);
    }

    @SuppressLint("SetTextI18n")
    private void setHarga(int tot) {
        int hitung = 0;
        if (tot > 0 && tot < 10) {
            hitung = tot * Integer.parseInt(modelHarga.getHarga1pcs().replace(".", "").trim());
        } else if (tot >= 10 && tot < 50) {
            hitung = tot * Integer.parseInt(modelHarga.getHarga10pcs().replace(".", "").trim());
        } else if (tot >= 50 && tot < 100) {
            hitung = tot * Integer.parseInt(modelHarga.getHarga50pcs().replace(".", "").trim());
        } else if (tot >= 100) {
            hitung = tot * Integer.parseInt(modelHarga.getHarga100pcs().replace(".", "").trim());
        }

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        tvTotalHarga.setText(formatRupiah.format((double) hitung));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void clear() {
        int size = this.dataset.size();
        this.dataset.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        CheckBox option;
        Button btnPlus, btnMin;
        TextView tvJml;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            option = itemView.findViewById(R.id.optionVarian);
            btnMin = itemView.findViewById(R.id.btnMin);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            tvJml = itemView.findViewById(R.id.tvJml);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString();
                Log.d(TAG, constraint.toString());
                ArrayList<ModelBarang> filter = new ArrayList<>();
                for (int i = 0; i < datasetFilter.size(); i++) {
                    if (datasetFilter.get(i).getNama_kategori().contains(constraint) || datasetFilter.get(i).getNama_satuan().contains(constraint)) {
                        ModelBarang m = datasetFilter.get(i);
                        filter.add(m);
                    }
                }
                filterResults.count = filter.size();
                filterResults.values = filter;
            } else {
                filterResults.count = datasetFilter.size();
                filterResults.values = datasetFilter;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataset = (ArrayList<ModelBarang>) results.values;
            notifyDataSetChanged();
        }
    }
}