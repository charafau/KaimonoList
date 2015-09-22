package com.nullpointerbay.retrolist.activity;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nullpointerbay.retrolist.MainApp;
import com.nullpointerbay.retrolist.R;
import com.nullpointerbay.retrolist.component.AppComponent;
import com.nullpointerbay.retrolist.fragment.ShopItemAddFragment;

public class ShopItemAddActivity extends BaseActivity {
    public static void start(Context context) {
        context.startActivity(new Intent(context, ShopItemAddActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.layout_container, new ShopItemAddFragment()).commit();

    }

    @Override
    protected void setupComponent(AppComponent component, MainApp mainApp) {
    }
}
