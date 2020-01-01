package com.example.pencatatantransaksi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pencatatantransaksi.Model.ModelBarang;
import com.example.pencatatantransaksi.Model.ModelKategori;
import com.example.pencatatantransaksi.R;

import java.util.List;

public class AdapterSpinnerVarian extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelBarang> item;

    public AdapterSpinnerVarian(Activity activity, List<ModelBarang> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_spinner_varian, null);

        TextView satuan = (TextView) convertView.findViewById(R.id.varian);

        ModelBarang data;
        data = item.get(position);

        satuan.setText(data.getVarian());

        return convertView;
    }
}
