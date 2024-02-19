package com.example.sportcity;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey("c7e17456-251a-4aec-97eb-5e6bc1280473");
    }
}
