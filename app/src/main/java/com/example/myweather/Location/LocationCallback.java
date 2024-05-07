package com.example.myweather.Location;

import com.baidu.location.BDLocation;

public interface LocationCallback {
    /**
     * 接收定位
     * @param bdLocation 定位数据
     */
    void onReceiveLocation(BDLocation bdLocation);
}
