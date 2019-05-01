package com.mai.pilot_assistent.di.component;

import com.mai.pilot_assistent.di.PerActivity;
import com.mai.pilot_assistent.di.module.ActivityModule;
import com.mai.pilot_assistent.ui.aircrafts.AircraftsActivity;
import com.mai.pilot_assistent.ui.login.LoginActivity;
import com.mai.pilot_assistent.ui.main.MainActivity;
import com.mai.pilot_assistent.ui.registration.RegistrationActivity;
import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(LoginActivity activity);
    void inject(RegistrationActivity activity);
    void inject(AircraftsActivity activity);

}
