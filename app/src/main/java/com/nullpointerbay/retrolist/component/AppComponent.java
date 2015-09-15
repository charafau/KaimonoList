package com.nullpointerbay.retrolist.component;

import com.nullpointerbay.retrolist.MainApp;
import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.module.AppModule;
import com.nullpointerbay.retrolist.module.DaoModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DaoModule.class})
public interface AppComponent {
    void inject(MainApp mainApp);

    ShopItemDao getShopItemDao();

}
