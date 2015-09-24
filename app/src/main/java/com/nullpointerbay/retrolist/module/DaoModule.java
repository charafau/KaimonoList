package com.nullpointerbay.retrolist.module;


import android.content.Context;

import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.dao.ShopItemDaoImpl;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class DaoModule {

    private final Context context;

    public DaoModule(Context context) {
        this.context = context;
    }

    @Provides
    public ShopItemDao provideShopItemDao() {
        return new ShopItemDaoImpl(Realm.getInstance(context), context);
    }

}
