package com.skeleton.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Contains access token and mapping of user details model with Data class
 */
public class Data implements Parcelable {


    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(final Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(final int size) {
            return new Data[size];
        }
    };
    @SerializedName("accessToken")
    private String accessToken;
    @SerializedName("userDetails")
    private UserDetails userDetails;

    /**
     * @param in in
     */
    protected Data(final Parcel in) {
        accessToken = in.readString();
        userDetails = in.readParcelable(UserDetails.class.getClassLoader());
    }


    /**
     * @return access token of the user generated from server
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken set access token for the current calling object
     */
    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return Object of the User Details Model
     */
    public UserDetails getUserDetails() {
        return userDetails;
    }

    /**
     * @param userDetails set object of User Details Model for the current calling object
     */
    public void setUserDetails(final UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(accessToken);
        dest.writeParcelable(userDetails, flags);
    }
}