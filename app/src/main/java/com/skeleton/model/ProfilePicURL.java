package com.skeleton.model;

import com.google.gson.annotations.SerializedName;

/**
 * Profile Pic came back from server
 */
public class ProfilePicURL {
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("original")
    private String original;

    /**
     * @return thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail set thumbnail
     */
    public void setThumbnail(final String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return Original Image
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @param original set original image
     */
    public void setOriginal(final String original) {
        this.original = original;
    }
}