package io.plan8.backoffice.model.api

import com.google.gson.annotations.SerializedName
import io.plan8.backoffice.model.BaseModel

/**
 * Created by SSozi on 2017. 11. 20..
 */
class AuthInfo: BaseModel {
    @SerializedName("user") lateinit var user: User
    @SerializedName("token") lateinit var token: String
}