package com.mai.pilot_assistent.ui.main;

import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.ui.base.BasePresenter;
import com.mai.pilot_assistent.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

import javax.inject.Inject;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>  implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getCurrentUser() {
        String userName = getDataManager().getCurrentUsername();

        getCompositeDisposable()
                .add(getDataManager()
                        .getUserByUsername(userName)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(res -> getMvpView().setCurrentUser(res),
                                   er -> getMvpView().onError("Жепа")));
    }
}