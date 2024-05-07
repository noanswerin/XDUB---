package com.example.myweather.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mynewlibrary.network.ApiType;
import com.example.mynewlibrary.network.NetworkApi;
import com.example.mynewlibrary.network.observer.BaseObserver;
import com.example.myweather.ApiService;
import com.example.myweather.db.bean.BingResponse;

@SuppressLint("CheckResult")
public class BingRepository {

    private static final String TAG = WeatherRepository.class.getSimpleName();

    private static final class BingRepositoryHolder {
        private static final BingRepository mInstance = new BingRepository();
    }

    public static BingRepository getInstance() {
        return BingRepositoryHolder.mInstance;
    }

    /**
     * 必应壁纸
     *
     * @param responseLiveData 成功数据
     * @param failed           错误信息
     */
    public void bing(MutableLiveData<BingResponse> responseLiveData, MutableLiveData<String> failed) {
        String type = "必应壁纸-->";
        NetworkApi.createService(ApiService.class, ApiType.BING).bing()
                .compose(NetworkApi.applySchedulers(new BaseObserver<BingResponse>() {
                    @Override
                    public void onSuccess(BingResponse bingResponse) {
                        if (bingResponse == null) {
                            failed.postValue("必应壁纸数据为null。");
                            return;
                        }
                        responseLiveData.postValue(bingResponse);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        Log.e(TAG, "onFailure: " + e.getMessage());
                        failed.postValue(type + e.getMessage());
                    }
                }));
    }
}

