package com.playlistapp.data.network.data.address.detail;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressDetailsInfo implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("is_receipent_visible")
    @Expose
    private boolean isRecipientVisible;
    @SerializedName("address_details")
    @Expose
    private List<AddressDetails> addressDetails;
    @SerializedName("description")
    @Expose
    private AddressDescription description;
    @SerializedName("important")
    @Expose
    private AddressImportant important;

    public AddressDetailsInfo() {}

    protected AddressDetailsInfo(Parcel in) {
        id = in.readInt();
        title = in.readString();
        isRecipientVisible = in.readByte() != 0;
        addressDetails = in.createTypedArrayList(AddressDetails.CREATOR);
        description = in.readParcelable(AddressDescription.class.getClassLoader());
        important = in.readParcelable(AddressImportant.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeByte((byte) (isRecipientVisible ? 1 : 0));
        dest.writeTypedList(addressDetails);
        dest.writeParcelable(description, flags);
        dest.writeParcelable(important, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddressDetailsInfo> CREATOR = new Creator<AddressDetailsInfo>() {
        @Override
        public AddressDetailsInfo createFromParcel(Parcel in) {
            return new AddressDetailsInfo(in);
        }

        @Override
        public AddressDetailsInfo[] newArray(int size) {
            return new AddressDetailsInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRecipientVisible() {
        return isRecipientVisible;
    }

    public void setRecipientVisible(boolean recipientVisible) {
        isRecipientVisible = recipientVisible;
    }

    public List<AddressDetails> getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(List<AddressDetails> addressDetails) {
        this.addressDetails = addressDetails;
    }

    public AddressDescription getDescription() {
        return description;
    }

    public void setDescription(AddressDescription description) {
        this.description = description;
    }

    public AddressImportant getImportant() {
        return important;
    }

    public void setImportant(AddressImportant important) {
        this.important = important;
    }
}
