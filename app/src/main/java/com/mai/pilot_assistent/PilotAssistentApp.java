package com.mai.pilot_assistent;

import android.app.Application;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.di.component.ApplicationComponent;
import com.mai.pilot_assistent.di.component.DaggerApplicationComponent;
import com.mai.pilot_assistent.di.module.ApplicationModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import javax.inject.Inject;

public class PilotAssistentApp extends Application {

    @Inject
    DataManager mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);


        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
