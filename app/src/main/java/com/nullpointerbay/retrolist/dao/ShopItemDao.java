package com.nullpointerbay.retrolist.dao;


import com.nullpointerbay.retrolist.model.ShopItem;

public interface ShopItemDao {
    void getItems(OnFinishedListener<ShopItem> listener);
}
