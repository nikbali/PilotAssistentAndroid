package com.mai.pilot_assistent.di.component;

import com.mai.pilot_assistent.di.PerService;
import com.mai.pilot_assistent.di.module.ServiceModule;
import dagger.Component;



@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

}
