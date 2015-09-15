package com.nullpointerbay.retrolist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nullpointerbay.retrolist.MainApp;
import com.nullpointerbay.retrolist.component.AppComponent;

/**
 * Created by charafau on 9/15/15.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent((AppComponent) MainApp.get(this).getComponent());
    }

    protected abstract void setupComponent(AppComponent component);
}
