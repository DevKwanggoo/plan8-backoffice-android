package io.plan8.backoffice.model.api

import com.google.gson.annotations.SerializedName
import io.plan8.backoffice.model.BaseModel

/**
 * Created by SSozi on 2017. 11. 20..
 */
class LoginInfo : BaseModel {
    @SerializedName("phoneNumber") lateinit var phoneNumber: String
    @SerializedName("code") lateinit var code: String
}