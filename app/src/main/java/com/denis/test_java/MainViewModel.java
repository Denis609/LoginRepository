package com.denis.test_java;

import androidx.lifecycle.ViewModel;

import com.denis.test_java.base.TokenSharedPrefs;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private final TokenSharedPrefs tokenSharedPrefs;

    @Inject
    public MainViewModel(TokenSharedPrefs tokenSharedPrefs) {
        this.tokenSharedPrefs = tokenSharedPrefs;
    }

    public Boolean authorize() {
        return tokenSharedPrefs.authorized();
    }
}
