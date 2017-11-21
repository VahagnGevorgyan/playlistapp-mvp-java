package com.playlistapp.eventbus.event;


public class OpenWebViewEvent {

    private String mWebUrl;
    private String mWebTitle;

    public OpenWebViewEvent(String webUrl, String webTitle) {
        this.mWebUrl = webUrl;
        this.mWebTitle = webTitle;
    }

    public String getWebUrl() {
        return this.mWebUrl;
    }

    public String getWebTitle() {
        return this.mWebTitle;
    }
}
