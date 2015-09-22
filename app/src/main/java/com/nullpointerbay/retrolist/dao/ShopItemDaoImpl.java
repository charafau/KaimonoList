package com.nullpointerbay.retrolist.dao;

import com.nullpointerbay.retrolist.model.ShopItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;


public class ShopItemDaoImpl implements ShopItemDao {

    private final Realm realm;

    public ShopItemDaoImpl(Realm realm) {
        this.realm = realm;
    }


    @Override
    public void getItems(OnFinishedListener<ShopItem> listener) {
        final RealmResults<ShopItem> all = realm.where(ShopItem.class).findAll();
        List<ShopItem> shopItems = new ArrayList<>(all.size());
        for (ShopItem item : all) {
            shopItems.add(item);
        }
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
