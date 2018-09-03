package com.ex.hotelfacilityagent;

import android.app.Application;

import com.ex.hotelfacilityagent.di.component.AppComponent;
import com.ex.hotelfacilityagent.di.component.DaggerAppComponent;
import com.ex.hotelfacilityagent.di.module.AppModule;
import com.ex.hotelfacilityagent.di.module.NetworkModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    private static final String DB_NAME = "Facility.Realm";
    private static final int REALM_SCHEMA = 1;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
        initializeRealmDB();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void initializeRealmDB() {
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(REALM_SCHEMA)
                .deleteRealmIfMigrationNeeded()
                .build());
    }
}
