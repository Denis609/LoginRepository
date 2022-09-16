package com.denis.test_java.ui;

import androidx.lifecycle.ViewModel;

import com.denis.test_java.api.AuthRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class SignInViewModel extends ViewModel {
    private final AuthRepository authRepository;

    @Inject
    public SignInViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void login(String login, String password) {
        authRepository.login(login, password)
                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(state -> {
////                    loading.postValue(true)
//                })
//                .doOnTerminate(
////                    loading.postValue(false)
//                })
                .subscribe(response -> {

                });
    }

}
