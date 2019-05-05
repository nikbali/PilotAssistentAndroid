package com.mai.pilot_assistent.ui.aircrafts.list;

import com.androidnetworking.error.ANError;
import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.data.db.model.Airport;
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

                                List<Aircraft> aircrafts = response.stream()
                                        .map(aircraftResponse -> {
                                            Aircraft aircraft = new Aircraft();
                                            if(aircraftResponse.getBaseAirport() != null){
                                                getDataManager().insertAirport(aircraftResponse.getBaseAirport().toAirport())
                                                        .subscribe(aircraft::setAirportId);
                                            }
                                            aircraft.setName(aircraftResponse.getName());
                                            aircraft.setIdServer(aircraftResponse.getId());
                                            aircraft.setYear(aircraftResponse.getYear());
                                            aircraft.setLength(aircraftResponse.getLength());
                                            aircraft.setWingspan(aircraftResponse.getWingspan());
                                            aircraft.setImageUrl(aircraftResponse.getImageUrl());
                                            aircraft.setHeight(aircraftResponse.getHeight());
                                            aircraft.setCruisingSpeed(aircraftResponse.getCruisingSpeed());
                                            aircraft.setMaxSpeed(aircraftResponse.getMaxSpeed());
                                            aircraft.setEnginePower(aircraftResponse.getEnginePower());
                                            aircraft.setRegistrationName(aircraftResponse.getRegistrationName());
                                            return aircraft;
                                        }
                                ).collect(Collectors.toList());

                                //ЧИСТИМ БД
                                getDataManager()
                                        .deleteAll()
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
                                //ЗАПИСЫВАЕМ В БД НОВЫЕ ДАННЫЕ
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

    @Override
    public Airport loadAirportById(long id) {
        return getDataManager().getAirportById(id);
    }
}

