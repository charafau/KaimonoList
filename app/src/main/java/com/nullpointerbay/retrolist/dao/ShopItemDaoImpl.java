package com.nullpointerbay.retrolist.dao;

import android.content.Context;

import com.nullpointerbay.retrolist.model.ShopItem;
import com.nullpointerbay.retrolist.model.realm.RealmShopItem;
import com.nullpointerbay.retrolist.rx.RealmObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;


public class ShopItemDaoImpl implements ShopItemDao {

    private final Realm realm;
    private Context context;

    public ShopItemDaoImpl(Realm realm, Context context) {
        this.realm = realm;
        this.context = context;
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
    public Observable<List<ShopItem>> listItems() {
        return RealmObservable.results(context,
                realmDb -> realmDb.where(RealmShopItem.class).findAll())
                .map(realmShopItems -> {
                    final List<ShopItem> items = new ArrayList<>(realmShopItems.size());
                    for (RealmShopItem realmItem : realmShopItems) {
                        items.add(shopItemFromRealm(realmItem));
                    }
                    return items;
                });
    }

    private ShopItem shopItemFromRealm(RealmShopItem realmItem) {
        return new ShopItem(realmItem.getId(), realmItem.getName());
    }

    @Override
    public Observable<ShopItem> newItem(String name) {
        final RealmShopItem realmShopItem = new RealmShopItem();
        realmShopItem.setName(name);
        return RealmObservable.object(context, r -> {
            return r.copyToRealm(realmShopItem);
        }).map(t -> shopItemFromRealm(realmShopItem));
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
