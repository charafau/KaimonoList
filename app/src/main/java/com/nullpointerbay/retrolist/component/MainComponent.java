package com.nullpointerbay.retrolist.component;

import com.nullpointerbay.retrolist.ActivityScope;
import com.nullpointerbay.retrolist.activity.MainActivity;
import com.nullpointerbay.retrolist.fragment.ShopItemListFragment;
import com.nullpointerbay.retrolist.module.DaoModule;
import com.nullpointerbay.retrolist.module.MainModule;
import com.nullpointerbay.retrolist.presenter.MainPresenter;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {MainModule.class, DaoModule.class}
)
public interface MainComponent {
    void inject(MainActivity activity);

    void inject(ShopItemListFragment fragment);

    MainPresenter getMainPresenter();

}
