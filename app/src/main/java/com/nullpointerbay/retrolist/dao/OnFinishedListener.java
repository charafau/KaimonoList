package com.nullpointerbay.retrolist.dao;

import java.util.List;


public interface OnFinishedListener<T> {

    void onFinished(List<T> items);
}
