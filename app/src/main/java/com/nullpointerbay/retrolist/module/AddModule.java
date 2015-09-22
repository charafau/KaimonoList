package com.nullpointerbay.retrolist.module;


import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.presenter.ShopItemAddPresenter;
import com.nullpointerbay.retrolist.presenter.ShopItemAddPresenterImpl;
import com.nullpointerbay.retrolist.view.ShopItemAddView;

import dagger.Module;
import dagger.Provides;

@Module
public class AddModule {
    private ShopItemAddView view;

    public AddModule(ShopItemAddView view) {
        this.view = view;
    }

    @Provides
    public ShopItemAddView provideView() {
        return view;
    }

    @Provides
    public ShopItemAddPresenter providePresenter(ShopItemAddView shopItemAddView, ShopItemDao dao) {
        return new ShopItemAddPresenterImpl(shopItemAddView, dao);
    }

}
