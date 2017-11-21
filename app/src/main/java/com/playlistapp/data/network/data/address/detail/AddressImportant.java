package com.playlistapp.data.network.data.address.detail;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressImportant implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("messages")
    @Expose
    private List<String> messages;

    public AddressImportant() {}

    protected AddressImportant(Parcel in) {
        title = in.readString();
        messages = in.createStringArrayList();
    }

    public static final Creator<AddressImportant> CREATOR = new Creator<AddressImportant>() {
        @Override
        public AddressImportant createFromParcel(Parcel in) {
            return new AddressImportant(in);
        }

        @Override
        public AddressImportant[] newArray(int size) {
            return new AddressImportant[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeStringList(messages);
    }
}