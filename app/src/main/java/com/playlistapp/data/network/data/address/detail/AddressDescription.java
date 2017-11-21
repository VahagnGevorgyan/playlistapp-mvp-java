package com.playlistapp.data.network.data.address.detail;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressDescription implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("messages")
    @Expose
    private List<String> messages;

    public AddressDescription() {}

    protected AddressDescription(Parcel in) {
        title = in.readString();
        messages = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeStringList(messages);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddressDescription> CREATOR = new Creator<AddressDescription>() {
        @Override
        public AddressDescription createFromParcel(Parcel in) {
            return new AddressDescription(in);
        }

        @Override
        public AddressDescription[] newArray(int size) {
            return new AddressDescription[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}