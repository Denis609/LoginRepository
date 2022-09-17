package com.denis.test.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {
    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public final MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public final MutableLiveData<String> error = new MutableLiveData<String>();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
