package com.mai.pilot_assistent.ui.flights;

import com.androidnetworking.error.ANError;
import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.data.network.model.CreateAircraftRequest;
import com.mai.pilot_assistent.ui.base.BasePresenter;
import com.mai.pilot_assistent.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;
import java.io.File;

public class CreateFlightPresenter<V extends CreateFlightMvpView> extends BasePresenter<V>
        implements CreateFlightMvpPresenter<V>
{
    @Inject
    public CreateFlightPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void createAircraft(File file, CreateAircraftRequest request) {
        getMvpView().showLoading();
        getCompositeDisposable()
                .add(getDataManager()
                        .doServerCreateAircraftApiCall(file, request)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(
                                response -> {
                                    if (!isViewAttached()) {
                                        return;
                                    }
                                    getMvpView().hideLoading();
                                    getMvpView().openAircraftsActivity();

                                },
                                error -> {
                                    if (!isViewAttached()) {
                                        return;
                                    }

                                    getMvpView().hideLoading();

                                    if (error instanceof ANError) {
                                        ANError anError = (ANError) error;
                                        handleApiError(anError);
                                    }
                                }));
    }
}
