package com.mai.pilot_assistent.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.mai.pilot_assistent.di.ActivityContext;
import com.mai.pilot_assistent.di.PerActivity;
import com.mai.pilot_assistent.ui.login.LoginMvpPresenter;
import com.mai.pilot_assistent.ui.login.LoginMvpView;
import com.mai.pilot_assistent.ui.login.LoginPresenter;
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
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
