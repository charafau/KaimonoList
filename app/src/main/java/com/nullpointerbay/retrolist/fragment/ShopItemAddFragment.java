package com.nullpointerbay.retrolist.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nullpointerbay.retrolist.MainApp;
import com.nullpointerbay.retrolist.R;
import com.nullpointerbay.retrolist.component.AppComponent;
import com.nullpointerbay.retrolist.component.DaggerAddComponent;
import com.nullpointerbay.retrolist.module.AddModule;
import com.nullpointerbay.retrolist.module.DaoModule;
import com.nullpointerbay.retrolist.presenter.ShopItemAddPresenter;
import com.nullpointerbay.retrolist.view.ShopItemAddView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopItemAddFragment extends BaseFragment implements ShopItemAddView {

    @Inject
    ShopItemAddPresenter presenter;
    @Bind(R.id.edit_item_name)
    EditText editItemName;

    public void setPresenter(ShopItemAddPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void setupComponent(AppComponent component, MainApp mainApp) {
        DaggerAddComponent.builder()
                .appComponent(component)
                .addModule(new AddModule(this))
                .daoModule(new DaoModule(getActivity()))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_shop_item_add, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void close() {
        getActivity().finish();
    }

    @OnClick(R.id.btn_add)
    void onClickAddButton() {
        presenter.addNewShopItem(editItemName.getText().toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
