package com.nullpointerbay.retrolist.component;


import com.nullpointerbay.retrolist.ActivityScope;
import com.nullpointerbay.retrolist.fragment.ChartFragment;
import com.nullpointerbay.retrolist.module.ChartModule;
import com.nullpointerbay.retrolist.module.DaoModule;
import com.nullpointerbay.retrolist.presenter.ChartPresenter;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {ChartModule.class, DaoModule.class}
)
public interface ChartComponent {
    void inject(ChartFragment fragment);

    ChartPresenter getChartPresenter();
}
