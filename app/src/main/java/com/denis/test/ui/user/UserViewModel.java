package com.denis.test.ui.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.denis.test.api.model.UserDto;
import com.denis.test.api.user.UserRepository;
import com.denis.test.base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class UserViewModel extends BaseViewModel {
    private final UserRepository userRepository;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final MutableLiveData<UserDto> _user = new MutableLiveData<UserDto>();
    public LiveData<UserDto> user = _user;

    public void getUser() {
        compositeDisposable.add(userRepository.getUser()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(state -> loading.postValue(true))
                .doOnTerminate(() -> loading.postValue(false))
                .subscribe(_user::postValue, e -> error.postValue(e.getMessage())));
    }
}
