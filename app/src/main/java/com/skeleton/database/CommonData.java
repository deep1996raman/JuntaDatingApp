package com.skeleton.database;

import com.skeleton.constant.PaperDbConstant;
import com.skeleton.model.UserDetails;

import io.paperdb.Paper;

/**
 * Developer: Saurabh Verma
 * Dated: 19-02-2017.
 */
public final class CommonData implements PaperDbConstant {
    /**
     * Empty Constructor
     * not called
     */
    private CommonData() {
    }

    /**
     * Update fcm token.
     *
     * @param token the token
     */
//======================================== FCM token ==============================================
    public static void updateFCMToken(final String token) {
        Paper.book().write(PAPER_DEVICE_TOKEN, token);
    }

    /**
     * Gets fcm token.
     *
     * @return the fcm token
     */
    public static String getFCMToken() {
        return Paper.book().read(PAPER_DEVICE_TOKEN);
    }

    /**
     * Save access token.
     *
     * @param accessToken the access token
     */
//======================================== Access Token ============================================
    public static void saveAccessToken(final String accessToken) {
        Paper.book().write(PAPER_ACCESS_TOKEN, accessToken);
    }

    /**
     * Gets access token.
     *
     * @return the access token
     */
    public static String getAccessToken() {
        return Paper.book().read(PAPER_ACCESS_TOKEN);
    }


    //======================================== Clear Data ===============================================

    /**
     * Delete paper.
     */
    public static void clearData() {
        String deviceToken = getFCMToken();
        Paper.book().destroy();
        updateFCMToken(deviceToken);
    }

    /**
     * @param userDetails model obj
     */
    public static void saveData(final UserDetails userDetails) {
        Paper.book().write(PAPER_SAVE_DATA, userDetails);
    }

    /**
     * @return get
     */
    public static UserDetails getData() {
        return Paper.book().read(PAPER_SAVE_DATA);
    }
}
