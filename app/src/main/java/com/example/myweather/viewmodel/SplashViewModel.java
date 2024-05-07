package com.example.myweather.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.mynewlibrary.base.BaseViewModel;
import com.example.myweather.db.bean.BingResponse;
import com.example.myweather.db.bean.Province;
import com.example.myweather.repository.BingRepository;
import com.example.myweather.repository.CityRepository;

import java.util.List;

public class SplashViewModel extends BaseViewModel {

    public MutableLiveData<List<Province>> listMutableLiveData = new MutableLiveData<>();

    /**
     * 添加城市数据
     */
    public void addCityData(List<Province> provinceList) {
        CityRepository.getInstance().addCityData(provinceList);
    }

    /**
     * 获取所有城市数据
     */
    public void getAllCityData() {
        CityRepository.getInstance().getCityData(listMutableLiveData);
    }


    public MutableLiveData<BingResponse> bingResponseMutableLiveData = new MutableLiveData<>();

    public void bing() {
        BingRepository.getInstance().bing(bingResponseMutableLiveData, failed);
    }

}

