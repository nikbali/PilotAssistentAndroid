package com.mai.pilot_assistent.ui.login;


import com.androidnetworking.error.ANError;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.data.db.model.User;
import com.mai.pilot_assistent.data.network.model.LoginRequest;
import com.mai.pilot_assistent.ui.base.BasePresenter;
import com.mai.pilot_assistent.utils.CommonUtils;
import com.mai.pilot_assistent.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;


public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onServerLoginClick(String email, String password) {
        if (email == null || email.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }

        if (password == null || password.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }
        getMvpView().showLoading();

        getCompositeDisposable()
                .add(getDataManager()
                        .doServerLoginApiCall(new LoginRequest(email, password))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            if(response.getErrorText() != null || response.getUserProfile() == null){
                                if (!isViewAttached()) {
                                    return;
                                }
                                getMvpView().hideLoading();
                                getMvpView().onError(response.getErrorText());

                            }else{
                                //добавляем в префы
                                getDataManager().updateUserInfoPrefs(
                                        response.getAccessToken(),
                                        response.getUserProfile().getId(),
                                        DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                        response.getUserProfile().getUsername(),
                                        response.getUserProfile().getEmail(),
                                        response.getUserProfile().getName());
                                //добавляем в бд
                                User user = new User(null,
                                        response.getUserProfile().getName(),
                                        response.getUserProfile().getId(),
                                        response.getUserProfile().getEmail(),
                                        response.getUserProfile().getGender(),
                                        response.getUserProfile().getBirth() != null ? CommonUtils.toDate(response.getUserProfile().getBirth()) : null,
                                        response.getUserProfile().getUsername());

                                getDataManager().insertUser(user).subscribe(suc -> {
                                    long i = suc;
                                }, eror -> {
                                    if (!isViewAttached()) {
                                        return;
                                    }

                                    getMvpView().hideLoading();

                                    if (eror instanceof ANError) {
                                        ANError anError = (ANError) eror;
                                        handleApiError(anError);
                                    }
                                });

                                if (!isViewAttached()) {
                                    return;
                                }
                                getMvpView().hideLoading();
                                getMvpView().openMainActivity();
                            }
                        }, throwable -> {

                            if (!isViewAttached()) {
                                return;
                            }

                            getMvpView().hideLoading();

                            if (throwable instanceof ANError) {
                                ANError anError = (ANError) throwable;
                                handleApiError(anError);
                            }
                        }));
    }

}
