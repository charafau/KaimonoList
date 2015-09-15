package com.nullpointerbay.retrolist.module;


import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.dao.ShopItemDaoImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {

    @Provides
    public ShopItemDao provideShopItemDao() {
        return new ShopItemDaoImpl();
    }

}
