package com.nullpointerbay.retrolist.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.nullpointerbay.retrolist.MainApp;
import com.nullpointerbay.retrolist.R;
import com.nullpointerbay.retrolist.adapter.ShopListItemAdapter;
import com.nullpointerbay.retrolist.component.AppComponent;
import com.nullpointerbay.retrolist.component.DaggerMainComponent;
import com.nullpointerbay.retrolist.model.ShopItem;
import com.nullpointerbay.retrolist.module.DaoModule;
import com.nullpointerbay.retrolist.module.MainModule;
import com.nullpointerbay.retrolist.presenter.MainPresenter;
import com.nullpointerbay.retrolist.view.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        listViewItems.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void setupComponent(AppComponent component, MainApp mainApp) {
        DaggerMainComponent.builder()
                .appComponent(component)
                .mainModule(new MainModule(this))
                .daoModule(new DaoModule(this))
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

    //create iterface for add view and call it in presenter
    @OnClick(R.id.fab)
    void onClickFabButton() {
        presenter.startAddView();
    }

    @Override
    public void setItems(List<ShopItem> items) {
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(items);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void startAddView() {
        ShopItemAddActivity.start(this);
    }
}
