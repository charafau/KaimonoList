package com.nullpointerbay.retrolist.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nullpointerbay.retrolist.R;
import com.nullpointerbay.retrolist.activity.ShopItemAddActivity;
import com.nullpointerbay.retrolist.adapter.ShopListItemAdapter;
import com.nullpointerbay.retrolist.component.AppComponent;
import com.nullpointerbay.retrolist.component.DaggerMainComponent;
import com.nullpointerbay.retrolist.model.ShopItem;
import com.nullpointerbay.retrolist.module.DaoModule;
import com.nullpointerbay.retrolist.module.MainModule;
import com.nullpointerbay.retrolist.presenter.MainPresenter;
import com.nullpointerbay.retrolist.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopItemListFragment extends BaseFragment implements MainView {

    @Inject
    MainPresenter presenter;
    ShopListItemAdapter adapter;
    @Bind(R.id.list_view_items)
    ListView listViewItems;

    @Inject
    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_shop_item_list, container, false);
        ButterKnife.bind(this, view);
        initializeAdapter();
        return view;
    }

    private void initializeAdapter() {
        this.adapter = new ShopListItemAdapter(getActivity());
        listViewItems.setAdapter(adapter);
    }

    @Override
    protected void setupComponent(AppComponent component) {
        DaggerMainComponent.builder()
                .appComponent(component)
                .mainModule(new MainModule(this))
                .daoModule(new DaoModule(getActivity()))
                .build()
                .inject(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    //create iterface for add view and call it in presenter
    @OnClick(R.id.fab)
    void onClickFabButton() {
        presenter.startAddView();
    }

    @Override
    public void setItems(List<ShopItem> items) {
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(items);
        }
    }

    @Override
    public void startAddView() {
        ShopItemAddActivity.start(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
