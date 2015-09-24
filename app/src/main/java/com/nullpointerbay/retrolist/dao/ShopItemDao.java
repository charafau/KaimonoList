package com.nullpointerbay.retrolist.dao;


import com.nullpointerbay.retrolist.model.ShopItem;

import java.util.List;

import rx.Observable;

public interface ShopItemDao {
    void getItems(OnFinishedListener<ShopItem> listener);

    Observable<List<ShopItem>> listItems();

    Observable<ShopItem> newItem(String name);

    void addItem(String name);
}
