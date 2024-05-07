package com.example.mynewlibrary.base;


import androidx.viewbinding.ViewBinding;

public abstract class NetworkActivity<VB extends ViewBinding> extends BaseVBActivity<VB> {

    @Override
    public void initData() {
        onCreate();
        onObserveData();
    }

    protected abstract void onCreate();

    protected abstract void onObserveData();
}
