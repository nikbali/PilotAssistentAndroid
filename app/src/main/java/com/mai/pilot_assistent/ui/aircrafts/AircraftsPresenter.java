package com.mai.pilot_assistent.ui.aircrafts;

import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.ui.base.BasePresenter;
import com.mai.pilot_assistent.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class AircraftsPresenter<V extends AircraftsMvpView> extends BasePresenter<V>
        implements AircraftsMvpPresenter<V> {

    @Inject
    public AircraftsPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
