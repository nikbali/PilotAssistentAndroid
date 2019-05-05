package com.mai.pilot_assistent.ui.flights;

import com.androidnetworking.error.ANError;
import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.ui.base.BasePresenter;
import com.mai.pilot_assistent.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class CreateFlightPresenter<V extends CreateFlightMvpView> extends BasePresenter<V>
        implements CreateFlightMvpPresenter<V>
{
    @Inject
    public CreateFlightPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void loadAircrafts() {
            getCompositeDisposable()
                    .add(getDataManager()
                            .getAllAircrafts()
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(response -> getMvpView().initSpinnerAircrafts(response),
                                    throwable -> {
                                if (throwable instanceof ANError) {
                                    ANError anError = (ANError) throwable;
                                    handleApiError(anError);
                                }
                            }));

    }
}
