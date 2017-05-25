package com.skeleton.model;

import com.google.gson.annotations.SerializedName;

/**
 * User Images
 */
public class UserImages {
    @SerializedName("id")
    private String id;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("original")
    private String original;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id set id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return thumbnail of the image
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail set thumbnail of the image
     */
    public void setThumbnail(final String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return Original of the User
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @param original set Original Image of the user
     */
    public void setOriginal(final String original) {
        this.original = original;
    }
}