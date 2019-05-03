package com.mai.pilot_assistent.di.component;

import com.mai.pilot_assistent.di.PerActivity;
import com.mai.pilot_assistent.di.module.ActivityModule;
import com.mai.pilot_assistent.ui.aircrafts.create.CreateAircraftActivity;
import com.mai.pilot_assistent.ui.aircrafts.details.AircraftDetailActivity;
import com.mai.pilot_assistent.ui.aircrafts.list.AircraftsActivity;
import com.mai.pilot_assistent.ui.login.LoginActivity;
import com.mai.pilot_assistent.ui.main.MainActivity;
import com.mai.pilot_assistent.ui.registration.RegistrationActivity;
import com.mai.pilot_assistent.ui.splash.SplashActivity;
import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);
    void inject(MainActivity activity);
    void inject(LoginActivity activity);
    void inject(RegistrationActivity activity);
    void inject(AircraftsActivity activity);
    void inject(AircraftDetailActivity activity);
    void inject(CreateAircraftActivity activity);
}
