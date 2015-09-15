package com.nullpointerbay.retrolist.dao;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.nullpointerbay.retrolist.model.ShopItem;

import java.util.List;


public class ShopItemDaoImpl implements ShopItemDao {
    @Override
    public void getItems(OnFinishedListener<ShopItem> listener) {
        final List<ShopItem> shopItems = Stream.ofRange(1, 10)
                .map(value -> new ShopItem("Item - " + value))
                .collect(Collectors.toList());
        listener.onFinished(shopItems);
    }
}
