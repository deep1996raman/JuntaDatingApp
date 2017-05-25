package com.skeleton.model.ProfileStep2;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abc on 21/5/17.
 */
public class Categories {
    @SerializedName("_id")
    private String id;
    @SerializedName("categoryType")
    private String categoryType;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("isDeleted")
    private boolean isDeleted;
    @SerializedName("picURL")
    private PicURL picURL;
    @SerializedName("name")
    private String name;

    /**
     * @return id
     */
    public String get_id() {
        return id;
    }

    /**
     * @param pid set id
     */
    public void set_id(final String pid) {
        this.id = pid;
    }

    /**
     * @return Category type
     */
    public String getCategoryType() {
        return categoryType;
    }

    /**
     * @param categoryType set category tyepe
     */
    public void setCategoryType(final String categoryType) {
        this.categoryType = categoryType;
    }

    /**
     * @return get update
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt set update
     */
    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return get created at
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt set create at
     */
    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return is Deleted
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * @param deleted set Deleted
     */
    public void setDeleted(final boolean deleted) {
        isDeleted = deleted;
    }

    /**
     * @return get Pic URL
     */
    public PicURL getPicURL() {
        return picURL;
    }

    /**
     * @param picURL set Pic URL
     */
    public void setPicURL(final PicURL picURL) {
        this.picURL = picURL;
    }

    /**
     * @return get name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name set name
     */
    public void setName(final String name) {
        this.name = name;
    }

}
