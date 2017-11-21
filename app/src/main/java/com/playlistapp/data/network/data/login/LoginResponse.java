package com.playlistapp.data.network.data.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playlistapp.data.network.data.ApiResponse;

public class LoginResponse extends ApiResponse {

    @SerializedName("data")
    @Expose
    private LoginData data;

    /**
     *
     * @return
     * The data
     */
    public LoginData getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(LoginData data) {
        this.data = data;
    }
}
