package com.playlistapp.data.network.data.city;


import com.google.gson.annotations.SerializedName;

public class CityItem {

    @SerializedName("name")
    private String mName;

    public String getName() {
        return mName;
    }
}