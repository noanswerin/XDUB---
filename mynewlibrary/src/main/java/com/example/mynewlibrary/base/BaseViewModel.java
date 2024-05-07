package com.example.mynewlibrary.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    public MutableLiveData<String> failed = new MutableLiveData<>();
}

