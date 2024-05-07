package com.example.myweather.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myweather.databinding.DialogCityBinding;
import com.example.myweather.db.bean.Province;
import com.example.myweather.ui.adapter.AdministrativeClickCallback;
import com.example.myweather.ui.adapter.AreaAdapter;
import com.example.myweather.ui.adapter.CityAdapter;
import com.example.myweather.ui.adapter.ProvinceAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.util.ArrayList;
import java.util.List;

public class CityDialog implements AdministrativeClickCallback {

    @SuppressLint("StaticFieldLeak")
    private static volatile CityDialog mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private BottomSheetDialog dialog;
    private DialogCityBinding binding;

    private final List<Province> provinceList = new ArrayList<>();
    private final List<Province.City> cityList = new ArrayList<>();
    private final List<Province.City.Area> areaList = new ArrayList<>();

    private final CityAdapter cityAdapter = new CityAdapter(cityList);
    private final AreaAdapter areaAdapter = new AreaAdapter(areaList);

    private SelectedCityCallback selectedCityCallback;

    private String provinceName, cityName, areaName;

    public CityDialog(Context context, List<Province> provinces) {
        mContext = context;
        provinceList.clear();
        provinceList.addAll(provinces);
    }

    public static CityDialog getInstance(Context context, List<Province> provinces) {
        if (mInstance == null) {
            synchronized (CityDialog.class) {
                if (mInstance == null) {
                    mInstance = new CityDialog(context, provinces);
                }
            }
        }
        return mInstance;
    }

    /**
     * 设置选中城市回调
     */
    public void setSelectedCityCallback(SelectedCityCallback selectedCityCallback) {
        this.selectedCityCallback = selectedCityCallback;
    }

    /**
     * 显示弹窗
     */
    public void show() {
        dialog = new BottomSheetDialog(mContext);
        binding = DialogCityBinding.inflate(LayoutInflater.from(mContext), null, false);
        ProvinceAdapter provinceAdapter = new ProvinceAdapter(provinceList);
        provinceAdapter.setAdministrativeClickCallback(this);
        cityAdapter.setAdministrativeClickCallback(this);
        areaAdapter.setAdministrativeClickCallback(this);
        binding.rvProvince.setLayoutManager(new LinearLayoutManager(mContext));
        binding.rvProvince.setAdapter(provinceAdapter);
        binding.rvCity.setLayoutManager(new LinearLayoutManager(mContext));
        binding.rvCity.setAdapter(cityAdapter);
        binding.rvArea.setLayoutManager(new LinearLayoutManager(mContext));
        binding.rvArea.setAdapter(areaAdapter);
        binding.ivClose.setOnClickListener(v -> dialog.dismiss());
        binding.ivSubmit.setOnClickListener(v -> {
            if (selectedCityCallback != null) {
                selectedCityCallback.selectedCity(areaName);
                dialog.dismiss();
            }
        });
        dialog.setContentView(binding.getRoot());
        dialog.show();
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onAdministrativeItemClick(View view, int position, AdministrativeType type) {
        switch (type) {
            case PROVINCE:
                cityList.clear();
                cityList.addAll(provinceList.get(position).getCityList());
                cityAdapter.notifyDataSetChanged();
                binding.rvCity.setVisibility(View.VISIBLE);
                binding.rvArea.setVisibility(View.GONE);
                provinceName = provinceList.get(position).getProvinceName();
                cityName = null;
                areaName = null;
                break;
            case CITY:
                areaList.clear();
                areaList.addAll(cityList.get(position).getAreaList());
                areaAdapter.notifyDataSetChanged();
                binding.rvArea.setVisibility(View.VISIBLE);
                cityName = cityList.get(position).getCityName();
                areaName = null;
                break;
            case AREA:
                areaName = areaList.get(position).getAreaName();
                break;
        }
        binding.ivSubmit.setVisibility(areaName == null ? View.INVISIBLE : View.VISIBLE);
        binding.tvTitle.setText(provinceName);
        if (cityName == null) return;
        binding.tvTitle.setText(provinceName + " > " + cityName);
        if (areaName == null) return;
        binding.tvTitle.setText(provinceName + " > " + cityName + " > " + areaName);
    }

    public interface SelectedCityCallback {
        void selectedCity(String cityName);
    }
}


