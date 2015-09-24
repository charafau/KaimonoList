package com.nullpointerbay.retrolist.presenter;


import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.view.ShopItemAddView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ShopItemAddPresenterImpl implements ShopItemAddPresenter {

    private final ShopItemAddView view;
    private final ShopItemDao dao;
    private CompositeSubscription compositeSubscription;


    public ShopItemAddPresenterImpl(ShopItemAddView view, ShopItemDao dao) {
        this.view = view;
        this.dao = dao;
    }

    @Override
    public void addNewShopItem(String name) {
        final Subscription subscription = dao.newItem(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(shopItem -> view.close());

        if (compositeSubscription != null) {
            compositeSubscription.add(subscription);
        }
    }

    @Override
    public void onResume() {
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onPause() {
        compositeSubscription.unsubscribe();
    }
}
