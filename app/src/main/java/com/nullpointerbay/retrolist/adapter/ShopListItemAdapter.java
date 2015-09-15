package com.nullpointerbay.retrolist.adapter;


import android.content.Context;
import android.widget.ArrayAdapter;

import com.nullpointerbay.retrolist.R;
import com.nullpointerbay.retrolist.model.ShopItem;

public class ShopListItemAdapter extends ArrayAdapter<ShopItem> {
    public ShopListItemAdapter(Context context) {
        super(context, R.layout.item_shop_list);
    }
}
