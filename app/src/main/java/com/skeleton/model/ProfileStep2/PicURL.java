package com.skeleton.model.ProfileStep2;

/**
 * Created by abc on 21/5/17.
 */


import com.google.gson.annotations.SerializedName;

/**
 * Developer: Sumit Thakur
 * Dated: 16-05-2017.
 */

public class PicURL {
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
     * @param thumbnail set ThumbNail
     */
    public void setThumbnail(final String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return get Original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @param original set Original
     */
    public void setOriginal(final String original) {
        this.original = original;
    }
}
