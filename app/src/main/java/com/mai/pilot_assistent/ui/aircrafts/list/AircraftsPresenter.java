package com.mai.pilot_assistent.ui.aircrafts.list;

import com.androidnetworking.error.ANError;
import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.ui.base.BasePresenter;
import com.mai.pilot_assistent.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class AircraftsPresenter<V extends AircraftsMvpView> extends BasePresenter<V>
        implements AircraftsMvpPresenter<V> {

    @Inject
    public AircraftsPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void refreshAircrafts() {
        getMvpView().showLoading();
        getCompositeDisposable()
                .add(getDataManager()
                        .doServerGetAircraftsApiCall()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            if (response == null || response.size() == 0) {
                                if (!isViewAttached()) {
                                    return;
                                }
                                getMvpView().hideLoading();
                            } else {
                                List<Aircraft> aircrafts = response.stream().map(aircraftResponse -> new Aircraft(null,
                                        aircraftResponse.getName(),
                                        aircraftResponse.getId(),
                                        aircraftResponse.getYear(),
                                        aircraftResponse.getLength(),
                                        aircraftResponse.getWingspan(),
                                        aircraftResponse.getImageUrl(),
                                        aircraftResponse.getHeight(),
                                        aircraftResponse.getCruisingSpeed(),
                                        aircraftResponse.getMaxSpeed(),
                                        aircraftResponse.getEnginePower())).collect(Collectors.toList());
                                getDataManager()
                                        .insertListAircraft(aircrafts)
                                        .subscribe(s -> {
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
                                                });

                                if (!isViewAttached()) {
                                    return;
                                }
                                getMvpView().refreshAircraftList(aircrafts);
                                getMvpView().hideLoading();
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

    @Override
    public void loadAircraftsFromDb() {
        getMvpView().showLoading();
        getCompositeDisposable()
                .add(getDataManager()
                        .getAllAircrafts()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            if (response == null || response.size() == 0) {
                                if (!isViewAttached()) {
                                    return;
                                }
                                getMvpView().hideLoading();
                            } else {
                                if (!isViewAttached()) {
                                    return;
                                }
                                getMvpView().refreshAircraftList(response);
                                getMvpView().hideLoading();
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

