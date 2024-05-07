package com.example.myweather;


import static com.example.myweather.Constant.API_KEY;

import com.example.myweather.db.bean.AirResponse;
import com.example.myweather.db.bean.BingResponse;
import com.example.myweather.db.bean.DailyResponse;
import com.example.myweather.db.bean.HourlyResponse;
import com.example.myweather.db.bean.LifestyleResponse;
import com.example.myweather.db.bean.NowResponse;
import com.example.myweather.db.bean.SearchCityResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 搜索城市  模糊搜索，国内范围 返回10条数据
     *
     * @param location 城市名
     * @param mode     exact 精准搜索  fuzzy 模糊搜索
     * @return NewSearchCityResponse 搜索城市数据返回
     */
    @GET("/v2/city/lookup?key=" + API_KEY + "&range=cn")
    Observable<SearchCityResponse> searchCity(@Query("location") String location,
                                              @Query("mode") String mode);

    @GET("/v7/weather/now?key=" + API_KEY)
    Observable<NowResponse> nowWeather(@Query("location") String location);


    @GET("/v7/weather/7d?key=" + API_KEY)
    Observable<DailyResponse> dailyWeather(@Query("location") String location);

    @GET("/v7/indices/1d?key=" + API_KEY)
    Observable<LifestyleResponse> lifestyle(@Query("type") String type, @Query("location") String location);

    @GET("/HPImageArchive.aspx?format=js&idx=0&n=1")
    Observable<BingResponse> bing();

    @GET("/v7/weather/24h?key=" + API_KEY)
    Observable<HourlyResponse> hourlyWeather(@Query("location") String location);

    @GET("/v7/air/now?key=" + API_KEY)
    Observable<AirResponse> airWeather(@Query("location") String location);

}


