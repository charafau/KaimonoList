package com.nullpointerbay.retrolist;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nullpointerbay.retrolist.component.AppComponent;
import com.nullpointerbay.retrolist.component.DaggerAppComponent;
import com.nullpointerbay.retrolist.module.AppModule;

import io.realm.Realm;
import io.realm.exceptions.RealmMigrationNeededException;


public class MainApp extends Application {

    private AppComponent component;

    public static MainApp get(Context context) {
        return (MainApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
//        configureDatabase();
    }

    private void configureDatabase() {
        try {
            Realm.getInstance(this);
        } catch (RealmMigrationNeededException e) {
            Log.i("Realm", "Migration of database needed. Executing...");
            Realm.getInstance(this);
        }
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
