package com.example.pencatatantransaksi.Master;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.pencatatantransaksi.R;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mSlider;
    CardView kategori, satuan, barang, info, input_harga, harga;


    public MasterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_master, container, false);

        mSlider = v.findViewById(R.id.slider);
        kategori = v.findViewById(R.id.kategori_card);
        satuan = v.findViewById(R.id.satuan_card);
        barang = v.findViewById(R.id.barang_card);
        info = v.findViewById(R.id.info_card);
        input_harga = v.findViewById(R.id.transaksiHarga_card);
        harga = v.findViewById(R.id.harga_card);

        HashMap<String, String> url_maps = new HashMap<>();
        url_maps.put("Morning Fresh", "http://sinau-newbie.esy.es/galeri/Morning-Fresh.jpg");
        url_maps.put("Stronger", "http://sinau-newbie.esy.es/galeri/Stronger-1.jpg");
        url_maps.put("Sweety", "http://sinau-newbie.esy.es/galeri/Sweety-1.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mSlider.addSlider(textSliderView);
        }

        mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(4000);
        mSlider.addOnPageChangeListener(this);

        kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(),Kategori.class);
                startActivity(a);
            }
        });

        satuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(getActivity(),Satuan.class);
                startActivity(b);
            }
        });

        barang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(getActivity(),Barang.class);
                startActivity(c);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(getActivity(),Stok.class);
                startActivity(d);
            }
        });

        input_harga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(getActivity(),InputHarga.class);
                startActivity(e);
            }
        });

        harga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(getActivity(),Harga.class);
                startActivity(f);
            }
        });
        return v;



    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
