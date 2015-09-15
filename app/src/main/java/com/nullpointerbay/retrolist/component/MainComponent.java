package com.nullpointerbay.retrolist.component;

import com.nullpointerbay.retrolist.ActivityScope;
import com.nullpointerbay.retrolist.activity.MainActivity;
import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.module.MainModule;
import com.nullpointerbay.retrolist.presenter.MainPresenter;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity activity);

    MainPresenter getMainPresenter();

    ShopItemDao getShopItemDao();

}
