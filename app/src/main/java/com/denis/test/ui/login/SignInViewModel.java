package com.denis.test.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.denis.test.api.auth.AuthRepository;
import com.denis.test.base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class SignInViewModel extends BaseViewModel {
    private final AuthRepository authRepository;

    @Inject
    public SignInViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    private final MutableLiveData<Boolean> _success = new MutableLiveData<Boolean>();
    public LiveData<Boolean> success = _success;

    public void login(String login, String password) {
        compositeDisposable.add(authRepository.login(login, password)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(state -> loading.postValue(true))
                .doOnTerminate(() -> loading.postValue(false))
                .subscribe(response -> _success.postValue(true), e -> error.postValue(e.getMessage())));
    }
}
