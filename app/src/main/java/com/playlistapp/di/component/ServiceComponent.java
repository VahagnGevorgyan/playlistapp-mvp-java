package com.playlistapp.di.component;


import com.playlistapp.di.PerService;
import com.playlistapp.di.module.ServiceModule;

import dagger.Component;


@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

//    void inject(GcmIntentService service);

}
