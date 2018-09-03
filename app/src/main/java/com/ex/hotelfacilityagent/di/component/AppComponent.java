package com.ex.hotelfacilityagent.di.component;

import com.ex.hotelfacilityagent.activity.MainActivity;
import com.ex.hotelfacilityagent.activity.FacilityActivity;
import com.ex.hotelfacilityagent.di.module.AppModule;
import com.ex.hotelfacilityagent.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(FacilityActivity facilityActivity);
}
