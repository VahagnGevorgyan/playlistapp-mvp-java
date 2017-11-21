package com.playlistapp.data.network.data.address.detail;


import com.playlistapp.data.network.data.BaseRequest;

public class AddressDetailsRequest extends BaseRequest {

    private int address_id;

    public AddressDetailsRequest(int addressId) {
        this.address_id = addressId;
    }

    public int getAddressId() {
        return address_id;
    }

    public void setAddressId(int addressId) {
        this.address_id = addressId;
    }
}
