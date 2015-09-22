package com.nullpointerbay.retrolist.fragment;


import android.app.Fragment;
import android.os.Bundle;

import com.nullpointerbay.retrolist.MainApp;
import com.nullpointerbay.retrolist.component.AppComponent;

public abstract class BaseFragment extends Fragment {
    public static final String TAG = BaseFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent((AppComponent) MainApp.get(getActivity()).getComponent(), MainApp.get(getActivity()));
    }

    protected abstract void setupComponent(AppComponent component, MainApp mainApp);
}
