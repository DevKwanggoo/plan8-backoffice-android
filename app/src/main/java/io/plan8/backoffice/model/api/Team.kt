package io.plan8.backoffice.model.api

import com.google.gson.annotations.SerializedName
import io.plan8.backoffice.model.BaseModel

/**
 * Created by SSozi on 2017. 11. 20..
 */
class Team: BaseModel {
    @SerializedName("id") lateinit var teamId: String
    @SerializedName("name") lateinit var teamName: String
    @SerializedName("logo") lateinit var teamLogo: String
    @SerializedName("configuration") lateinit var configuration: Configuration
    @SerializedName("ownerId") lateinit var ownerId: String
    @SerializedName("deactivated") var deactivated: Boolean = true

    //TODO : member, admin 추가해야함
}