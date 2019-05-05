package com.mai.pilot_assistent.ui.aircrafts.create;

import com.androidnetworking.error.ANError;
import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.data.db.model.Airport;
import com.mai.pilot_assistent.data.network.model.CreateAircraftRequest;
import com.mai.pilot_assistent.ui.base.BasePresenter;
import com.mai.pilot_assistent.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class CreateAircraftPresenter<V extends CreateAircraftMvpView> extends BasePresenter<V>
        implements CreateAircraftMvpPresenter<V> {
    @Inject
    public CreateAircraftPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
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

    @Override
    public void loadAirports() {
        getDataManager()
                .getAllAirports().subscribe(succes -> {
                    if (!succes.isEmpty()) {
                        getMvpView().initSpinnerAirports(succes);
                    } else {
                        getDataManager()
                                .doServerGetAirportsApiCall()
                                .subscribeOn(getSchedulerProvider().io())
                                .observeOn(getSchedulerProvider().ui())
                                .subscribe(airportResponses -> {
                                    List<Airport> airportList = airportResponses
                                            .stream()
                                            .map(r -> r.toAirport())
                                            .collect(Collectors.toList());
                                    getDataManager().insertListAirport(airportList)
                                            .subscribe(success ->{
                                                getMvpView().initSpinnerAirports(airportList);
                                            }, ex -> {
                                                if (ex instanceof ANError) {
                                                    ANError anError = (ANError) ex;
                                                    handleApiError(anError);
                                                }
                                            });

                                }, error -> {
                                    if (error instanceof ANError) {
                                        ANError anError = (ANError) error;
                                        handleApiError(anError);
                                    }
                                });
                    }
                }
                , error -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    if (error instanceof ANError) {
                        ANError anError = (ANError) error;
                        handleApiError(anError);
                    }
                });
    }

    @Override
    public Airport loadAirportByName(String name) {
        return getDataManager()
                .getAirportByName(name);
    }
}
