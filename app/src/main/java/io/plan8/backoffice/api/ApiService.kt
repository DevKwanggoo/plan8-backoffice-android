package io.plan8.backoffice.api

import io.plan8.backoffice.model.api.LoginInfo
import io.plan8.backoffice.model.api.AuthInfo
import io.plan8.backoffice.model.api.Me
import io.plan8.backoffice.model.api.Team
import retrofit2.Call
import retrofit2.http.*

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
}
