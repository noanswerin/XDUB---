package com.example.myweather.viewmodel;


import androidx.lifecycle.MutableLiveData;

import com.example.mynewlibrary.base.BaseViewModel;
import com.example.myweather.db.bean.AirResponse;
import com.example.myweather.db.bean.DailyResponse;
import com.example.myweather.db.bean.HourlyResponse;
import com.example.myweather.db.bean.LifestyleResponse;
import com.example.myweather.db.bean.NowResponse;
import com.example.myweather.db.bean.Province;
import com.example.myweather.db.bean.SearchCityResponse;
import com.example.myweather.repository.CityRepository;
import com.example.myweather.repository.SearchCityRepository;
import com.example.myweather.repository.WeatherRepository;

import java.util.List;

public class MainViewModel extends BaseViewModel {

    public MutableLiveData<SearchCityResponse> searchCityResponseMutableLiveData = new MutableLiveData<>();

    /**
     * 搜索成功
     * @param cityName 城市名称
     */
    public void searchCity(String cityName) {
        new SearchCityRepository().searchCity(searchCityResponseMutableLiveData, failed, cityName,true);
    }


    public MutableLiveData<NowResponse> nowResponseMutableLiveData = new MutableLiveData<>();

    public void nowWeather(String cityId) {
        new WeatherRepository().getInstance().nowWeather(nowResponseMutableLiveData,failed, cityId);
    }

    public MutableLiveData<DailyResponse> dailyResponseMutableLiveData = new MutableLiveData<>();

    public void dailyWeather(String cityId) {
        new WeatherRepository().getInstance().dailyWeather(dailyResponseMutableLiveData, failed, cityId);
    }

    public MutableLiveData<LifestyleResponse> lifestyleResponseMutableLiveData = new MutableLiveData<>();

    public void lifestyle(String cityId) {
        WeatherRepository.getInstance().lifestyle(lifestyleResponseMutableLiveData, failed, cityId);
    }

    public MutableLiveData<List<Province>> cityMutableLiveData = new MutableLiveData<>();

    public void getAllCity() {
        CityRepository.getInstance().getCityData(cityMutableLiveData);
    }

    public MutableLiveData<HourlyResponse> hourlyResponseMutableLiveData = new MutableLiveData<>();

    public void hourlyWeather(String cityId) {
        WeatherRepository.getInstance().hourlyWeather(hourlyResponseMutableLiveData, failed, cityId);
    }

    public MutableLiveData<AirResponse> airResponseMutableLiveData = new MutableLiveData<>();

    public void airWeather(String cityId) {
        WeatherRepository.getInstance().airWeather(airResponseMutableLiveData, failed, cityId);
    }

}

