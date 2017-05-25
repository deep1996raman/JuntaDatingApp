package com.skeleton.model.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by abc on 21/5/17.
 */

public class ProfileConstants {

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ProfileConstantsData constantsData;

    /**
     * @return code
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode status code
     */
    public void setStatusCode(final Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return data
     */
    public ProfileConstantsData getConstantsData() {
        return constantsData;
    }
}
