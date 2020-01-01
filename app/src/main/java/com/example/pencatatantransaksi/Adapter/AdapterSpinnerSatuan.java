package com.example.pencatatantransaksi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pencatatantransaksi.Model.ModelSatuan;
import com.example.pencatatantransaksi.R;

import java.util.List;

public class AdapterSpinnerSatuan extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelSatuan> item;

    public AdapterSpinnerSatuan(Activity activity, List<ModelSatuan> item) {
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
            convertView = inflater.inflate(R.layout.list_spinner_satuan, null);

        TextView satuan = (TextView) convertView.findViewById(R.id.satuan);

        ModelSatuan data;
        data = item.get(position);

        satuan.setText(data.getNama_satuan());

        return convertView;
    }
}
