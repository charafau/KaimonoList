package com.nullpointerbay.retrolist.module;


import android.content.Context;

import com.nullpointerbay.retrolist.MainApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private MainApp app;

    public AppModule(MainApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideApplication() {
        return app;
    }
}
