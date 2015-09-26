package com.nullpointerbay.retrolist.dao;

import android.content.Context;

import com.nullpointerbay.retrolist.model.Category;
import com.nullpointerbay.retrolist.model.ShopItem;
import com.nullpointerbay.retrolist.model.realm.RealmCategory;
import com.nullpointerbay.retrolist.model.realm.RealmShopItem;
import com.nullpointerbay.retrolist.rx.RealmObservable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import rx.Observable;


public class ShopItemDaoImpl implements ShopItemDao {

    private final Realm realm;
    private Context context;

    public ShopItemDaoImpl(Realm realm, Context context) {
        this.realm = realm;
        this.context = context;
    }

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
        return new ShopItem(realmItem.getId(), realmItem.getName(), new Date());
    }

    public Observable<ShopItem> newItem(String name) {
        final RealmShopItem realmShopItem = new RealmShopItem();
        realmShopItem.setName(name);
        return RealmObservable.object(context, r -> {
            return r.copyToRealm(realmShopItem);
        }).map(t -> shopItemFromRealm(realmShopItem));
    }

    public Observable<List<Category>> listCategories() {

        return RealmObservable.results(context,
                realmDb -> realmDb.where(RealmCategory.class).findAll())
                .map(realmCategories -> {
                    final List<Category> categories = new ArrayList<Category>(realmCategories.size());
                    for (RealmCategory realmCategory : realmCategories) {
                        categories.add(new Category(realmCategory.getId(), realmCategory.getName()));
                    }
                    return categories;
                });
    }
}
