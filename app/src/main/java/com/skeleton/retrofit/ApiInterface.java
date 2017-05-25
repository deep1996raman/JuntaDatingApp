package com.skeleton.retrofit;


import com.skeleton.model.CommanResponse;
import com.skeleton.model.InterestCategories;
import com.skeleton.model.Profile.ProfileConstants;
import com.skeleton.model.ProfileStep2.Response;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

import static com.skeleton.constant.ApiKeyConstant.AUTHORIZATION;
import static com.skeleton.constant.AppConstant.REQUEST_TYPE;

/**
 * Developer: Saurabh Verma
 * Dated: 27-09-2016.
 */
public interface ApiInterface {
    String UPDATE_LOCATION = "api/v1/user/updateLocation";
    String USER_SIGNUP = "api/user/register";
    String USER_LOGIN = "api/user/login";
    String USER_OTP = "api/user/resendOTP";
    String SKIP_STEP = "api/user/skipStep ";
    String RESEND_OTP = "api/user/resendOTP";
    String GET_USER_PROFILE = "api/user/getProfile";
    String VERIFY_OTP = "api/user/verifyOTP";
    String PROFILE_CONSTANTS = "api/profile/constants";
    String USER_IMAGE_CONSTANTS = "api/category/list";
    String CATEGORY_LIST = "api/category/list";
    String UPDATE_PROFILE = "api/user/updateProfile";
    String SELECT_CATEGORY = "api/user/selectCategory";


//    /**
//     * @param map
//     * @return
//     */
//    @Multipart
//    @POST("api/v1/user/createUser")
//    Call<LoginResponse> register(@PartMap HashMap<String, RequestBody> map);
//
//
//    /**
//     * @param map
//     * @return
//     */
//    @FormUrlEncoded
//    @PUT("api/v1/user/socialLogin")
//    Call<LoginResponse> socialLogin(@FieldMap HashMap<String, String> map);
//
//    /**
//     * @param authorization
//     * @param map
//     * @return
//     */
//    @FormUrlEncoded
//    @PUT("api/v1/user/loginToken")
//    Call<LoginResponse> accessTokenLogin(@Header(AUTHORIZATION) String authorization,
//                                         @FieldMap HashMap<String, String> map);
//
//
//    /**
//     * @param email
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/v1/user/forgotpassword")
//    Call<CommonResponse> forgotPassword(@Field("email") String email);
//
//    /**
//     * @param map
//     * @return
//     */
//    @FormUrlEncoded
//    @PUT("api/v1/user/loginCredential")
//    Call<LoginResponse> login(@FieldMap HashMap<String, String> map);


    /**
     * Update location call.
     *
     * @param authorization the authorization
     * @param map           the map
     * @return the call
     */
    @FormUrlEncoded
    @POST(UPDATE_LOCATION)
    Call<CommonParams> updateLocation(@Header(AUTHORIZATION) String authorization,
                                      @FieldMap HashMap<String, String> map);

    /**
     * @param hashMap map
     * @return status call
     */
    @Multipart
    @POST(USER_SIGNUP)
    Call<CommanResponse> userRegister(@PartMap HashMap<String, RequestBody> hashMap);

    /**
     * @param stringHashMap call
     * @return login call
     */
    @FormUrlEncoded
    @POST(USER_LOGIN)
    Call<CommanResponse> userLogin(@FieldMap HashMap<String, String> stringHashMap);

    /**
     * @param mAuthorization parameter
     * @return profile
     */
    @GET(GET_USER_PROFILE)
    Call<CommanResponse> getUserProfile(@Header(AUTHORIZATION) String mAuthorization);

    /**
     * @param mAuthorization paramter
     * @param stringHashMap  paramter
     * @return t/f
     */
    @FormUrlEncoded
    @PUT(VERIFY_OTP)
    Call<CommanResponse> verifyOtp(@Header(AUTHORIZATION) String mAuthorization, @FieldMap HashMap<String, String> stringHashMap);

    /**
     * @return constant profile data
     */
    @GET(PROFILE_CONSTANTS)
    Call<ProfileConstants> profileConstant();


    /**
     * @param authorization authorization
     * @param requestType   request type
     * @return return the image constaants
     */
    @GET(USER_IMAGE_CONSTANTS)
    Call<Response> profileImageSet(@Header(AUTHORIZATION) String authorization,
                                   @Query(REQUEST_TYPE) String requestType);

    /**
     * @param authorization authorization
     * @param map           map
     * @return return
     */
    @Multipart
    @PUT(UPDATE_PROFILE)
    Call<CommanResponse> updateProfile(@Header(AUTHORIZATION) String authorization, @PartMap HashMap<String, RequestBody> map);

    /**
     * @param auth       Access Token of the user
     * @param stepNumber Which number to skipped
     * @return Response from server
     */
    @FormUrlEncoded
    @PUT(SKIP_STEP)
    Call<ProfileConstants> skipStep(@Header(AUTHORIZATION) String auth, @Field("stepNumber") int stepNumber);

    /**
     * @param auth        Access Token of the user
     * @param requestType request type
     * @return Categories of the user might be interested in
     */
    @GET(CATEGORY_LIST)
    Call<InterestCategories> getCategories(@Header(AUTHORIZATION) String auth, @Query("requestType") String requestType);


    /**
     * @param authorization Access Token of the user
     * @param map           key value pair of date updated by user
     * @return Response from server
     */
    @FormUrlEncoded
    @PUT(SELECT_CATEGORY)
    Call<CommanResponse> selectCategory(@Header(AUTHORIZATION) String authorization,
                                        @FieldMap HashMap<String, String> map);

    /**
     * @param authorization string
     * @return hello
     */
    @PUT(RESEND_OTP)
    Call<CommanResponse> reSendOtp(@Header(AUTHORIZATION) String authorization);

}



