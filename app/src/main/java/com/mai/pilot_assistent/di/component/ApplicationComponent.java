package com.mai.pilot_assistent.di.component;

import android.app.Application;
import android.content.Context;
import com.mai.pilot_assistent.PilotAssistentApp;
import com.mai.pilot_assistent.data.DataManager;
import com.mai.pilot_assistent.di.ApplicationContext;
import com.mai.pilot_assistent.di.module.ApplicationModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(PilotAssistentApp app);


    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}