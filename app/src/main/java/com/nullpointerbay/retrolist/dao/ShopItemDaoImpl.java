package com.nullpointerbay.retrolist.dao;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.nullpointerbay.retrolist.model.ShopItem;

import java.util.List;
import java.util.UUID;

import io.realm.Realm;


public class ShopItemDaoImpl implements ShopItemDao {

    private final Realm realm;

    public ShopItemDaoImpl(Realm realm) {
        this.realm = realm;
    }


    @Override
    public void getItems(OnFinishedListener<ShopItem> listener) {
        final List<ShopItem> shopItems = Stream.ofRange(1, 10)
                .map(value -> new ShopItem(String.valueOf(value), "Item - " + value))
                .collect(Collectors.toList());
        listener.onFinished(shopItems);
    }

    @Override
    public void addItem(String name) {
        realm.beginTransaction();
        final ShopItem shopItem = realm.createObject(ShopItem.class);
        shopItem.setName(name);
        shopItem.setId(UUID.randomUUID().toString());
        realm.commitTransaction();
    }
}
