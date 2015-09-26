package com.nullpointerbay.retrolist.presenter;


import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.view.ChartView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ChartPresenterImpl implements ChartPresenter {

    private final ChartView chartView;
    private final ShopItemDao shopItemDao;

    private CompositeSubscription compositeSubscription;

    public ChartPresenterImpl(ChartView chartView, ShopItemDao shopItemDao) {
        this.chartView = chartView;
        this.shopItemDao = shopItemDao;
    }

    @Override
    public void onResume() {

        compositeSubscription = new CompositeSubscription();
        final Subscription subscription = shopItemDao.listCategories().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(categories -> chartView.setCategories(categories));

        if (compositeSubscription != null) {
            compositeSubscription.add(subscription);
        }
    }

    @Override
    public void onPause() {
        compositeSubscription.unsubscribe();
    }

    @Override
    public void startAddView() {
        chartView.startAddView();
    }
}
