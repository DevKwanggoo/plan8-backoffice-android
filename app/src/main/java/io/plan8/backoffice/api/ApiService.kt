package io.plan8.backoffice.api

import io.plan8.backoffice.model.api.*
import retrofit2.Call
import retrofit2.http.*
import okhttp3.MultipartBody
import okhttp3.RequestBody


/**
 * Created by SSozi on 2017. 11. 7..
 */

interface ApiService {
    @FormUrlEncoded
    @POST("1/auth/pin-code")
    fun getPinCode(@Field("phoneNumber") phoneNumber: String): Call<LoginInfo>

    @FormUrlEncoded
    @POST("1/auth/token")
    fun getAuthInfo(@Field("code") code: String, @Field("pinCode") pinCode: String): Call<AuthInfo>

    @GET("1/me")
    fun getMe(@Header("authorization") authorization: String): Call<Me>

    @GET("1/me/teams")
    fun getTeam(@Header("authorization") authorization: String): Call<List<Team>>

    @Multipart
    @POST("1/upload")
    fun postUpload(@Header("authorization") authorization: String, @Part("files") files: RequestBody): Call<List<UploadInfo>>

    @FormUrlEncoded
    @PUT("1/users/me")
    fun putMe(@Header("authorization") authorization: String, @FieldMap putMeMap: HashMap<String, String>): Call<Me>
}
