package com.nullpointerbay.retrolist.presenter;

import com.nullpointerbay.retrolist.dao.OnFinishedListener;
import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.model.ShopItem;
import com.nullpointerbay.retrolist.view.MainView;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class MainPresenterImpl implements MainPresenter, OnFinishedListener<ShopItem> {
    private final MainView mainView;
    private final ShopItemDao shopItemDao;

    private CompositeSubscription compositeSubscription;

    public MainPresenterImpl(MainView mainView, ShopItemDao shopItemDao) {
        this.mainView = mainView;
        this.shopItemDao = shopItemDao;
    }

    @Override
    public void onResume() {
        mainView.showProgress();
        compositeSubscription = new CompositeSubscription();
        final Subscription subscribion = shopItemDao.listItems().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(shopItems -> mainView.setItems(shopItems),
                        throwable -> mainView.showProgress());

        if (compositeSubscription != null) {
            compositeSubscription.add(subscribion);
        }
    }

    @Override
    public void onPause() {
        compositeSubscription.unsubscribe();
    }

    @Override
    public void startAddView() {
        mainView.startAddView();
    }

    @Override
    public void onFinished(List<ShopItem> items) {
        mainView.setItems(items);
        mainView.hideProgress();
    }
}
