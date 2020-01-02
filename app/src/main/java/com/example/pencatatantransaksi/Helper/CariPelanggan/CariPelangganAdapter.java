package com.example.pencatatantransaksi.Helper.CariPelanggan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;

import java.util.ArrayList;

import android.widget.Filter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pencatatantransaksi.R;

public class CariPelangganAdapter extends RecyclerView.Adapter<CariPelangganAdapter.ViewHolder> implements Filterable {

    private static final String TAG = CariPelangganAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<PelangganModel> dataset;
    private ArrayList<PelangganModel> datasetFilter;
    private static ClickListener clickListener;
    private CustomFilter filter;

    public CariPelangganAdapter(Context context, ArrayList<PelangganModel> dataset) {
        this.context = context;
        this.dataset = dataset;
        this.datasetFilter = dataset;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        CariPelangganAdapter.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_pelanggan, parent, false);
        return new ViewHolder(view);
    }

    public interface ClickListener {
        void onItemClick(int position, View view);

        void onItemLongClick(int position, View view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PelangganModel item = dataset.get(position);
        holder.tvNama.setText(item.getPlgNama());
        holder.tvAlamat.setText(item.getPlgAlamat());
        holder.tvTelp.setText(item.getPlgTelp());
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

        private TextView tvNama, tvAlamat, tvTelp;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvTelp = itemView.findViewById(R.id.tvTelp);
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
                ArrayList<PelangganModel> filter = new ArrayList<>();
                for (int i = 0; i < datasetFilter.size(); i++) {
                    if (datasetFilter.get(i).getPlgNama().contains(constraint) || datasetFilter.get(i).getPlgTelp().contains(constraint)) {
                        PelangganModel m = datasetFilter.get(i);
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
            dataset = (ArrayList<PelangganModel>) results.values;
            notifyDataSetChanged();
        }
    }
}