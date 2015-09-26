package com.nullpointerbay.retrolist.module;

import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.presenter.ChartPresenter;
import com.nullpointerbay.retrolist.presenter.ChartPresenterImpl;
import com.nullpointerbay.retrolist.view.ChartView;

import dagger.Module;
import dagger.Provides;

@Module
public class ChartModule {
    private ChartView chartView;

    public ChartModule(ChartView chartView) {
        this.chartView = chartView;
    }

    @Provides
    public ChartView provideView() {
        return chartView;
    }

    @Provides
    public ChartPresenter providePresenter(ChartView chartView, ShopItemDao shopItemDao) {
        return new ChartPresenterImpl(chartView, shopItemDao);
    }
}
