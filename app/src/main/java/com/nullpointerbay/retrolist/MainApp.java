package com.nullpointerbay.retrolist;

import android.app.Application;
import android.content.Context;

import com.nullpointerbay.retrolist.component.AppComponent;
import com.nullpointerbay.retrolist.component.DaggerAppComponent;
import com.nullpointerbay.retrolist.module.AppModule;


public class MainApp extends Application {

    private AppComponent component;

    public static MainApp get(Context context) {
        return (MainApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
    }

    private void setupGraph() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public AppComponent getComponent() {
        return component;
    }
}
