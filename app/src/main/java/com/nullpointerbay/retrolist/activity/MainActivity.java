package com.nullpointerbay.retrolist.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.nullpointerbay.retrolist.R;
import com.nullpointerbay.retrolist.adapter.ShopListItemAdapter;
import com.nullpointerbay.retrolist.component.AppComponent;
import com.nullpointerbay.retrolist.component.DaggerMainComponent;
import com.nullpointerbay.retrolist.model.ShopItem;
import com.nullpointerbay.retrolist.module.MainModule;
import com.nullpointerbay.retrolist.presenter.MainPresenter;
import com.nullpointerbay.retrolist.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter presenter;

    ShopListItemAdapter adapter;
    @Bind(R.id.list_view_items)
    ListView listViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeAdapter();
    }

    private void initializeAdapter() {
        this.adapter = new ShopListItemAdapter(this);
        final List<ShopItem> shopItems = Stream.ofRange(1, 100)
                .map(value -> new ShopItem("first"))
                .collect(Collectors.toList());
        adapter.addAll(shopItems);
        listViewItems.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void setupComponent(AppComponent component) {
        DaggerMainComponent.builder()
                .appComponent(component)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setItems(List<ShopItem> items) {
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(items);
        }
    }
}
