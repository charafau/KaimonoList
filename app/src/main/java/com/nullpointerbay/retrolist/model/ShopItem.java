package com.nullpointerbay.retrolist.model;


import java.util.Date;

public class ShopItem {

    private final String id;

    private final String name;

    private final Date boughtAt;

    public ShopItem(String id, String name, Date boughtAt) {
        this.id = id;
        this.name = name;
        this.boughtAt = boughtAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBoughtAt() {
        return boughtAt;
    }
}
