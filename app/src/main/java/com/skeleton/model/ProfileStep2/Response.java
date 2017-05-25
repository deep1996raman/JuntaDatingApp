package com.skeleton.model.ProfileStep2;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abc on 21/5/17.
 */


public class Response {


    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;

    /**
     * @return status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode status code
     */
    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return get Message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message setMessage
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return get data
     */
    public Data getData() {
        return data;
    }


    /**
     * @param data set data
     */
    public void setData(final Data data) {
        this.data = data;
    }
}