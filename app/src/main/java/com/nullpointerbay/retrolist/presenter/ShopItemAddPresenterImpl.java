package com.nullpointerbay.retrolist.presenter;


import com.nullpointerbay.retrolist.dao.ShopItemDao;
import com.nullpointerbay.retrolist.view.ShopItemAddView;

public class ShopItemAddPresenterImpl implements ShopItemAddPresenter {

    private final ShopItemAddView view;
    private final ShopItemDao dao;

    public ShopItemAddPresenterImpl(ShopItemAddView view, ShopItemDao dao) {
        this.view = view;
        this.dao = dao;
    }

    @Override
    public void addNewShopItem(String name) {
        dao.addItem(name);
        view.close();
    }
}
