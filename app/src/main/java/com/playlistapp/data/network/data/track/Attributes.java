package com.playlistapp.data.network.data.track;


import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {
    @SerializedName("country")
    @Expose
    @Nullable
    private String mCountry;
    @SerializedName("page")
    @Expose
    @Nullable
    private String mPage;
    @SerializedName("perPage")
    @Expose
    @Nullable
    private String mPerPage;
    @SerializedName("totalPages")
    @Expose
    @Nullable
    private String mTotalPages;
    @SerializedName("total")
    @Expose
    @Nullable
    private String mTotal;

    @Nullable
    public String getCountry() {
        return mCountry;
    }

    @Nullable
    public String getPage() {
        return mPage;
    }

    @Nullable
    public String getPerPage() {
        return mPerPage;
    }

    @Nullable
    public String getTotalPages() {
        return mTotalPages;
    }

    @Nullable
    public String getTotal() {
        return mTotal;
    }
}
