package com.nullpointerbay.retrolist.component;


import com.nullpointerbay.retrolist.ActivityScope;
import com.nullpointerbay.retrolist.fragment.ShopItemAddFragment;
import com.nullpointerbay.retrolist.module.AddModule;
import com.nullpointerbay.retrolist.module.DaoModule;
import com.nullpointerbay.retrolist.presenter.ShopItemAddPresenter;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {AddModule.class, DaoModule.class}
)
public interface AddComponent {
    void inject(ShopItemAddFragment fragment);

    ShopItemAddPresenter getShopItemAddPresenter();
}
