package com.nullpointerbay.retrolist;

import android.app.Application;
import android.content.Context;

import com.nullpointerbay.retrolist.component.AppComponent;
import com.nullpointerbay.retrolist.component.DaggerAppComponent;
import com.nullpointerbay.retrolist.model.realm.RealmCategory;
import com.nullpointerbay.retrolist.module.AppModule;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainApp extends Application {

    private AppComponent component;

    public static MainApp get(Context context) {
        return (MainApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
        configureDatabase();
    }

    private void configureDatabase() {
        final Realm realm = Realm.getInstance(this);
        final RealmResults<RealmCategory> all =
                realm.where(RealmCategory.class).findAll();
        if (all.size() < 1) {
            final String[] startCategories = getResources().getStringArray(R.array.start_categories);
            for (String category : startCategories) {
                realm.beginTransaction();
                final RealmCategory realmCategory = realm.createObject(RealmCategory.class);
                realmCategory.setName(category);
                realmCategory.setId(UUID.randomUUID().toString());
                realm.commitTransaction();
            }
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
