package com.nullpointerbay.retrolist.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nullpointerbay.retrolist.R;
import com.nullpointerbay.retrolist.model.ShopItem;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShopListItemAdapter extends ArrayAdapter<ShopItem> {
    public ShopListItemAdapter(Context context) {
        super(context, R.layout.item_shop_list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null || convertView.getTag() == null) {
            final LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_shop_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        bindView(holder, getItem(position));

        return convertView;
    }

    private void bindView(ViewHolder holder, ShopItem item) {
        holder.itemInitial.setText(item.getName().substring(0, 1).toUpperCase());
        holder.txtShoppingItemName.setText(item.getName());
    }


    static class ViewHolder {
        @Bind(R.id.item_initial)
        TextView itemInitial;
        @Bind(R.id.txt_shopping_item_name)
        TextView txtShoppingItemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
