package com.mai.pilot_assistent.ui.registration;

import com.mai.pilot_assistent.data.DataManager;
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
    public void onRegistrationClick(String email, String password) {

    }
}
