package com.mai.pilot_assistent.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.mai.pilot_assistent.di.ActivityContext;
import com.mai.pilot_assistent.di.PerActivity;
import com.mai.pilot_assistent.ui.aircrafts.create.CreateAircraftMvpPresenter;
import com.mai.pilot_assistent.ui.aircrafts.create.CreateAircraftMvpView;
import com.mai.pilot_assistent.ui.aircrafts.create.CreateAircraftPresenter;
import com.mai.pilot_assistent.ui.aircrafts.list.AircraftsAdapter;
import com.mai.pilot_assistent.ui.aircrafts.list.AircraftsMvpPresenter;
import com.mai.pilot_assistent.ui.aircrafts.list.AircraftsMvpView;
import com.mai.pilot_assistent.ui.aircrafts.list.AircraftsPresenter;
import com.mai.pilot_assistent.ui.login.LoginMvpPresenter;
import com.mai.pilot_assistent.ui.login.LoginMvpView;
import com.mai.pilot_assistent.ui.login.LoginPresenter;
import com.mai.pilot_assistent.ui.main.MainMvpPresenter;
import com.mai.pilot_assistent.ui.main.MainMvpView;
import com.mai.pilot_assistent.ui.main.MainPresenter;
import com.mai.pilot_assistent.ui.registration.RegistrationMvpPresenter;
import com.mai.pilot_assistent.ui.registration.RegistrationMvpView;
import com.mai.pilot_assistent.ui.registration.RegistrationPresenter;
import com.mai.pilot_assistent.utils.rx.AppSchedulerProvider;
import com.mai.pilot_assistent.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    RegistrationMvpPresenter<RegistrationMvpView> provideRegistrationPresenter(
            RegistrationPresenter<RegistrationMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AircraftsMvpPresenter<AircraftsMvpView> provideAircraftsPresenter(AircraftsPresenter<AircraftsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    CreateAircraftMvpPresenter<CreateAircraftMvpView> provideCreateAircraftMvpPresenter(CreateAircraftPresenter<CreateAircraftMvpView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
