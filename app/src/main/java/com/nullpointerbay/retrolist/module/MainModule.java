package com.nullpointerbay.retrolist.module;

import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.presenter.MainPresenter;
import com.nullpointerbay.retrolist.presenter.MainPresenterImpl;
import com.nullpointerbay.retrolist.view.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    public MainView provideView() {
        return view;
    }

    @Provides
    public MainPresenter providePresenter(MainView mainView, ShopItemDao shopItemDao) {
        return new MainPresenterImpl(mainView, shopItemDao);
    }

}
