package com.skeleton.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abc on 20/5/17.
 */

public class CommanResponse implements Parcelable {
    /**
     * creator
     */
    public static final Creator<CommanResponse> CREATOR = new Creator<CommanResponse>() {
        @Override
        public CommanResponse createFromParcel(final Parcel in) {
            return new CommanResponse(in);
        }

        @Override
        public CommanResponse[] newArray(final int size) {
            return new CommanResponse[size];
        }
    };

    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;

    /**
     * @param in in
     */
    protected CommanResponse(final Parcel in) {
        statusCode = in.readInt();
        message = in.readString();
        data = in.readParcelable(Data.class.getClassLoader());
    }

    /**
     * @return Status code from server example 200,404...
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode of current calling object
     */
    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return message from server example success,failure
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message of current calling object
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return Object of Data Model Class
     */
    public Data getData() {
        return data;
    }


    /**
     * @param data set Data model of object for current calling object
     */
    public void setData(final Data data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(statusCode);
        dest.writeString(message);
        dest.writeParcelable(data, flags);
    }
}

