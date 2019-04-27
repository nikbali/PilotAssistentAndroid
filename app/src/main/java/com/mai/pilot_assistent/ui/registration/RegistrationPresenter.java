package com.mai.pilot_assistent.ui.registration;

import com.androidnetworking.error.ANError;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.data.network.model.LoginRequest;
import com.mai.pilot_assistent.data.network.model.RegistrationRequest;
import com.mai.pilot_assistent.ui.base.BasePresenter;
import com.mai.pilot_assistent.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class RegistrationPresenter<V extends RegistrationMvpView> extends BasePresenter<V>
        implements RegistrationMvpPresenter<V> {

    @Inject
    public RegistrationPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onRegistrationClick(RegistrationRequest registrationRequest) {
        if (registrationRequest.getEmail() == null || registrationRequest.getEmail().isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if (registrationRequest.getName() == null || registrationRequest.getName().isEmpty()) {
            getMvpView().onError(R.string.empty_username);
            return;
        }
        if (registrationRequest.getUsername() == null || registrationRequest.getUsername().isEmpty()) {
            getMvpView().onError(R.string.empty_username);
            return;
        }
        if (registrationRequest.getPassword() == null || registrationRequest.getPassword().isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }

        getMvpView().showLoading();

        getCompositeDisposable()
                .add(getDataManager()
                        .doServerRegistrationApiCall(registrationRequest)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            if(response.getSuccess().equals(false)){
                                if (!isViewAttached()) {
                                    return;
                                }
                                getMvpView().hideLoading();
                                getMvpView().onError(response.getMessage());

                            }else{
                                if (!isViewAttached()) {
                                    return;
                                }
                                getMvpView().hideLoading();
                                getMvpView().showMessage(response.getMessage());
                                getMvpView().backToLoginActivity();
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
