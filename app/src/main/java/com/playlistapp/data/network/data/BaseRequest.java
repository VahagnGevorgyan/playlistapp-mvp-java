package com.playlistapp.data.network.data;


public class BaseRequest {

    private int userid;
    private String token;
    private String devtype = "Android";

    public BaseRequest() {
        // Empty constructor
    }

    public BaseRequest(int userId, String token) {
        this.userid = userId;
        this.token = token;
    }

    public void setUserId(int userId) {
        this.userid = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
