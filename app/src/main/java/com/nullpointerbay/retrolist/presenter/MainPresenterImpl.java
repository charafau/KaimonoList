package com.nullpointerbay.retrolist.presenter;

import com.nullpointerbay.retrolist.dao.OnFinishedListener;
import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.model.ShopItem;
import com.nullpointerbay.retrolist.view.MainView;

import java.util.List;


public class MainPresenterImpl implements MainPresenter, OnFinishedListener<ShopItem> {
    private final MainView mainView;
    private final ShopItemDao shopItemDao;

    public MainPresenterImpl(MainView mainView, ShopItemDao shopItemDao) {
        this.mainView = mainView;
        this.shopItemDao = shopItemDao;
    }

    @Override
    public void onResume() {
        mainView.showProgress();
        shopItemDao.getItems(this);
    }

    @Override
    public void onFinished(List<ShopItem> items) {
        mainView.setItems(items);
        mainView.hideProgress();
    }
}
