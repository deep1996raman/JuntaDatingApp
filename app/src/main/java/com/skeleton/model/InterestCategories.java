package com.skeleton.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Interest Categories of the User
 */
public class InterestCategories {
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data interestData;

    /**
     * @return status code from server
     */
    public Integer getStatusCode() {
        return statusCode;
    }


    /**
     * @return message from server
     */
    public String getMessage() {
        return message;
    }


    /**
     * @return Object of the interest data from server
     */
    public Data getInterestData() {
        return interestData;
    }


}