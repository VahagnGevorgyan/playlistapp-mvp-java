package com.playlistapp.data.network.data.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("userid")
    @Expose
    private int userid;
    @SerializedName("isperson")
    @Expose
    private String isperson;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("companyname")
    @Expose
    private String companyname;

    /**
     *
     * @return
     * The token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return
     * The userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     *
     * @param userid
     * The userid
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     *
     * @return
     * The isperson
     */
    public String getIsperson() {
        return isperson;
    }

    /**
     *
     * @param isperson
     * The isperson
     */
    public void setIsperson(String isperson) {
        this.isperson = isperson;
    }

    /**
     *
     * @return
     * The firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     * The firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     * The lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     * The lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return
     * The companyname
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     *
     * @param companyname
     * The companyname
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
