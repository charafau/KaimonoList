package com.nullpointerbay.retrolist.dao;


import com.nullpointerbay.retrolist.model.Category;
import com.nullpointerbay.retrolist.model.ShopItem;

import java.util.List;

import rx.Observable;

public interface ShopItemDao {

    Observable<List<ShopItem>> listItems();

    Observable<ShopItem> newItem(String name);

    Observable<List<Category>> listCategories();
}
