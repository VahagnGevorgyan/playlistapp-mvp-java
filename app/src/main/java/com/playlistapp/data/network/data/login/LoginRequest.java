package com.playlistapp.data.network.data.login;


public class LoginRequest {
    private String devtype;
    private String email;
    private String pass;

    public LoginRequest(String email, String pass) {
        this.devtype = "Android";
        this.email = email;
        this.pass = pass;
    }

    public String getDevtype() {
        return devtype;
    }

    public void setDevtype(String devtype) {
        this.devtype = devtype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
