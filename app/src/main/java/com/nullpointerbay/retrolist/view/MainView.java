package com.nullpointerbay.retrolist.view;


import com.nullpointerbay.retrolist.model.ShopItem;

import java.util.List;

public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<ShopItem> items);
}
