package com.skeleton.model.Profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abc on 21/5/17.
 */

public class ProfileConstantsData {


    @SerializedName("Orientation")
    @Expose
    private List<String> orientation = null;
    @SerializedName("RelationshipHistory")
    @Expose
    private List<String> relationshipHistory = null;
    @SerializedName("Ethnicity")
    @Expose
    private List<String> ethnicity = null;
    @SerializedName("Religion")
    @Expose
    private List<String> religion = null;
    @SerializedName("BodyType")
    @Expose
    private List<String> bodyType = null;
    @SerializedName("Smoking")
    @Expose
    private List<String> smoking = null;
    @SerializedName("Drinking")
    @Expose
    private List<String> drinking = null;
    @SerializedName("Height")
    @Expose
    private List<String> height = null;

    /**
     * @return orientraion list
     */
    public List<String> getOrientation() {
        return orientation;
    }

    /**
     * @param orientation parameter
     */
    public void setOrientation(final List<String> orientation) {
        this.orientation = orientation;
    }

    /**
     * @return history
     */
    public List<String> getRelationshipHistory() {
        return relationshipHistory;
    }

    /**
     * @param relationshipHistory hisroty
     */
    public void setRelationshipHistory(final List<String> relationshipHistory) {
        this.relationshipHistory = relationshipHistory;
    }

    /**
     * @return ethticity
     */
    public List<String> getEthnicity() {
        return ethnicity;
    }

    /**
     * @return religon
     */
    public List<String> getReligion() {
        return religion;
    }

    /**
     * @return body
     */
    public List<String> getBodyType() {
        return bodyType;
    }

    /**
     * @return smoking
     */

    public List<String> getSmoking() {
        return smoking;
    }

    /**
     * @return driking
     */
    public List<String> getDrinking() {
        return drinking;
    }

    /**
     * @return height
     */
    public List<String> getHeight() {
        return height;
    }

}
