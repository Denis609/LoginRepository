package com.denis.test;

import androidx.lifecycle.ViewModel;

import com.denis.test.base.TokenSharedPrefs;

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
