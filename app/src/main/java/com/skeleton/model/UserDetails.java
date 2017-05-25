package com.skeleton.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Contains All Details of the Current User came back from server
 */
public class UserDetails implements Parcelable {

    public static final Creator<UserDetails> CREATOR = new Creator<UserDetails>() {
        @Override
        public UserDetails createFromParcel(final Parcel in) {
            return new UserDetails(in);
        }

        @Override
        public UserDetails[] newArray(final int size) {
            return new UserDetails[size];
        }
    };
    @SerializedName("_id")
    private String id;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("countryCode")
    private String countryCode;
    @SerializedName("dob")
    private String dob;
    @SerializedName("email")
    private String email;
    @SerializedName("phoneNo")
    private String phoneNo;
    @SerializedName("newNumber")
    private String newNumber;
    @SerializedName("userImages")
    private List<UserImages> userImages;
    @SerializedName("adminDeactivateAccount")
    private boolean adminDeactivateAccount;
    @SerializedName("timeOffset")
    private int timeOffset;
    @SerializedName("gender")
    private String gender;
    @SerializedName("aboutMe")
    private String aboutMe;
    @SerializedName("step2CompleteOrSkip")
    private boolean step2CompleteOrSkip;
    @SerializedName("step1CompleteOrSkip")
    private boolean step1CompleteOrSkip;
    @SerializedName("interestCategories")
    private List<InterestCategories> interestCategories;
    @SerializedName("profilePicURL")
    private ProfilePicURL profilePicURL;
    @SerializedName("defaultAddressId")
    private String defaultAddressId;
    @SerializedName("phoneVerified")
    private boolean phoneVerified;
    @SerializedName("emailVerified")
    private boolean emailVerified;
    @SerializedName("drinking")
    private String drinking;
    @SerializedName("smoking")
    private String smoking;
    @SerializedName("bodyType")
    private String bodyType;
    @SerializedName("height")
    private String height;
    @SerializedName("religion")
    private String religion;
    @SerializedName("ethnicity")
    private String ethnicity;
    @SerializedName("relationshipHistory")
    private String relationshipHistory;
    @SerializedName("notificationEnable")
    private boolean notificationEnable;
    @SerializedName("directDateRequestEnable")
    private boolean directDateRequestEnable;
    @SerializedName("privacy")
    private boolean privacy;
    @SerializedName("isDisable")
    private boolean isDisable;
    @SerializedName("language")
    private String language;
    @SerializedName("firstTimeLogin")
    private boolean firstTimeLogin;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("firstName")
    private String firstName;

    /**
     * @param in in
     */
    protected UserDetails(final Parcel in) {
        id = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        countryCode = in.readString();
        dob = in.readString();
        email = in.readString();
        phoneNo = in.readString();
        newNumber = in.readString();
        adminDeactivateAccount = in.readByte() != 0;
        timeOffset = in.readInt();
        gender = in.readString();
        aboutMe = in.readString();
        step2CompleteOrSkip = in.readByte() != 0;
        step1CompleteOrSkip = in.readByte() != 0;
        defaultAddressId = in.readString();
        phoneVerified = in.readByte() != 0;
        emailVerified = in.readByte() != 0;
        drinking = in.readString();
        smoking = in.readString();
        bodyType = in.readString();
        height = in.readString();
        religion = in.readString();
        ethnicity = in.readString();
        relationshipHistory = in.readString();
        notificationEnable = in.readByte() != 0;
        directDateRequestEnable = in.readByte() != 0;
        privacy = in.readByte() != 0;
        isDisable = in.readByte() != 0;
        language = in.readString();
        firstTimeLogin = in.readByte() != 0;
        lastName = in.readString();
        firstName = in.readString();
    }


    /**
     * @return if of the user
     */
    public String get_id() {
        return id;
    }

    /**
     * @param id set id of the user from current calling object
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return date and time at which user got registered on the server
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt set date and time of the user from current calling object
     */
    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return last time at which user details where updated on server
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt set last time at which user details where updated on server
     */
    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return Country Code of the user
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode set Country Code of the user
     */
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return Date of Birth of the User
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob set dob for the current calling object
     */
    public void setDob(final String dob) {
        this.dob = dob;
    }

    /**
     * @return Email Address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email set Email Address of the user for current calling object
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return Phone Number of the User
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo set Phone Number of the User for the current calling object
     */
    public void setPhoneNo(final String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return combination of the country code and phone number of the User
     */
    public String getNewNumber() {
        return newNumber;
    }

    /**
     * @param newNumber combination of the country code and phone number of the User
     */
    public void setNewNumber(final String newNumber) {
        this.newNumber = newNumber;
    }

    /**
     * @return List of the User Profile Images
     */
    public List<UserImages> getUserImages() {
        return userImages;
    }

    /**
     * @param userImages List of the User Profile Images
     */
    public void setUserImages(final List<UserImages> userImages) {
        this.userImages = userImages;
    }

    /**
     * @return true or false if admin account is deactivated
     */
    public boolean getAdminDeactivateAccount() {
        return adminDeactivateAccount;
    }

    /**
     * @param adminDeactivateAccount true or false for admin account deactivation
     */
    public void setAdminDeactivateAccount(final boolean adminDeactivateAccount) {
        this.adminDeactivateAccount = adminDeactivateAccount;
    }

    /**
     * @return time offset
     */
    public int getTimeOffset() {
        return timeOffset;
    }

    /**
     * @param timeOffset time offset
     */
    public void setTimeOffset(final int timeOffset) {
        this.timeOffset = timeOffset;
    }

    /**
     * @return Gender of the User
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender set gender of the User
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }

    /**
     * @return Bio of the User
     */
    public String getAboutMe() {
        return aboutMe;
    }

    /**
     * @param aboutMe Bio of the User
     */
    public void setAboutMe(final String aboutMe) {
        this.aboutMe = aboutMe;
    }

    /**
     * @return true of false if step 2 is skipped
     */
    public boolean getStep2CompleteOrSkip() {
        return step2CompleteOrSkip;
    }

    /**
     * @param step2CompleteOrSkip set true of false if step 2 is skipped
     */
    public void setStep2CompleteOrSkip(final boolean step2CompleteOrSkip) {
        this.step2CompleteOrSkip = step2CompleteOrSkip;
    }

    /**
     * @return true of false if step 1 is skipped
     */
    public boolean getStep1CompleteOrSkip() {
        return step1CompleteOrSkip;
    }

    /**
     * @param step1CompleteOrSkip set  true of false if step 2 is skipped
     */
    public void setStep1CompleteOrSkip(final boolean step1CompleteOrSkip) {
        this.step1CompleteOrSkip = step1CompleteOrSkip;
    }

    /**
     * @return List of Interest Categories of the user
     */
    public List<InterestCategories> getInterestCategories() {
        return interestCategories;
    }

    /**
     * @param interestCategories set List of Interest Categories of the user
     */
    public void setInterestCategories(final List<InterestCategories> interestCategories) {
        this.interestCategories = interestCategories;
    }

    /**
     * @return Url of the User Profile Pic
     */
    public ProfilePicURL getProfilePicURL() {
        return profilePicURL;
    }

    /**
     * @param profilePicURL set Url of the User Profile Pic
     */
    public void setProfilePicURL(final ProfilePicURL profilePicURL) {
        this.profilePicURL = profilePicURL;
    }

    /**
     * @return default address id of the User
     */
    public String getDefaultAddressId() {
        return defaultAddressId;
    }

    /**
     * @param defaultAddressId set default address id of the User
     */
    public void setDefaultAddressId(final String defaultAddressId) {
        this.defaultAddressId = defaultAddressId;
    }

    /**
     * @return true or false if phone number is verified or not
     */
    public boolean getPhoneVerified() {
        return phoneVerified;
    }

    /**
     * @param phoneVerified set true or false if phone number is verified or not
     */
    public void setPhoneVerified(final boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    /**
     * @return true or false if email is verified or not
     */
    public boolean getEmailVerified() {
        return emailVerified;
    }

    /**
     * @param emailVerified set true or false if email is verified or not
     */
    public void setEmailVerified(final boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    /**
     * @return drinking
     */
    public String getDrinking() {
        return drinking;
    }

    /**
     * @param drinking no idea
     */
    public void setDrinking(final String drinking) {
        this.drinking = drinking;
    }

    /**
     * @return smoking
     */
    public String getSmoking() {
        return smoking;
    }

    /**
     * @param smoking no idea
     */
    public void setSmoking(final String smoking) {
        this.smoking = smoking;
    }

    /**
     * @return body type of the User
     */
    public String getBodyType() {
        return bodyType;
    }

    /**
     * @param bodyType set body type of the User
     */
    public void setBodyType(final String bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * @return height of the User
     */
    public String getHeight() {
        return height;
    }

    /**
     * @param height set height of the User
     */
    public void setHeight(final String height) {
        this.height = height;
    }

    /**
     * @return religion of the User
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion set religion of the User
     */
    public void setReligion(final String religion) {
        this.religion = religion;
    }

    /**
     * @return Ethnicity of the User
     */
    public String getEthnicity() {
        return ethnicity;
    }

    /**
     * @param ethnicity set Ethnicity of the User
     */
    public void setEthnicity(final String ethnicity) {
        this.ethnicity = ethnicity;
    }

    /**
     * @return RelationshipHistory of the User
     */
    public String getRelationshipHistory() {
        return relationshipHistory;
    }

    /**
     * @param relationshipHistory set RelationshipHistory of the User
     */
    public void setRelationshipHistory(final String relationshipHistory) {
        this.relationshipHistory = relationshipHistory;
    }

    /**
     * @return if Notification is Enabled or not
     */
    public boolean getNotificationEnable() {
        return notificationEnable;
    }

    /**
     * @param notificationEnable set if Notification is Enabled or not
     */
    public void setNotificationEnable(final boolean notificationEnable) {
        this.notificationEnable = notificationEnable;
    }

    /**
     * @return true or false if Direct Date Request Enable or not
     */
    public boolean getDirectDateRequestEnable() {
        return directDateRequestEnable;
    }

    /**
     * @param directDateRequestEnable set true or false if Direct Date Request Enable or not
     */
    public void setDirectDateRequestEnable(final boolean directDateRequestEnable) {
        this.directDateRequestEnable = directDateRequestEnable;
    }

    /**
     * @return true or false if privacy enabled or not
     */
    public boolean getPrivacy() {
        return privacy;
    }

    /**
     * @param privacy set true or false if privacy enabled or not
     */
    public void setPrivacy(final boolean privacy) {
        this.privacy = privacy;
    }

    /**
     * @return Disabled
     */
    public boolean getIsDisable() {
        return isDisable;
    }

    /**
     * @param isDisable no idea
     */
    public void setIsDisable(final boolean isDisable) {
        this.isDisable = isDisable;
    }

    /**
     * @return Language of the user
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language set Language of the user
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

    /**
     * @return true or false if First Time Login or not
     */
    public boolean getFirstTimeLogin() {
        return firstTimeLogin;
    }

    /**
     * @param firstTimeLogin set true or false if First Time Login or not
     */
    public void setFirstTimeLogin(final boolean firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }

    /**
     * @return Last Name of the User
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName set Last Name of the User
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return First Name of the User
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName set First Name of the User
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(id);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(countryCode);
        dest.writeString(dob);
        dest.writeString(email);
        dest.writeString(phoneNo);
        dest.writeString(newNumber);
        dest.writeByte((byte) (adminDeactivateAccount ? 1 : 0));
        dest.writeInt(timeOffset);
        dest.writeString(gender);
        dest.writeString(aboutMe);
        dest.writeByte((byte) (step2CompleteOrSkip ? 1 : 0));
        dest.writeByte((byte) (step1CompleteOrSkip ? 1 : 0));
        dest.writeString(defaultAddressId);
        dest.writeByte((byte) (phoneVerified ? 1 : 0));
        dest.writeByte((byte) (emailVerified ? 1 : 0));
        dest.writeString(drinking);
        dest.writeString(smoking);
        dest.writeString(bodyType);
        dest.writeString(height);
        dest.writeString(religion);
        dest.writeString(ethnicity);
        dest.writeString(relationshipHistory);
        dest.writeByte((byte) (notificationEnable ? 1 : 0));
        dest.writeByte((byte) (directDateRequestEnable ? 1 : 0));
        dest.writeByte((byte) (privacy ? 1 : 0));
        dest.writeByte((byte) (isDisable ? 1 : 0));
        dest.writeString(language);
        dest.writeByte((byte) (firstTimeLogin ? 1 : 0));
        dest.writeString(lastName);
        dest.writeString(firstName);
    }
}