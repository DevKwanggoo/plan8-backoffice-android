package io.plan8.backoffice.model.api

import com.google.gson.annotations.SerializedName
import io.plan8.backoffice.model.BaseModel

/**
 * Created by SSozi on 2017. 11. 20..
 */
class Me: BaseModel{
    @SerializedName("id") lateinit var userId: String
    @SerializedName("created") lateinit var userCreated: String
    @SerializedName("updated") lateinit var updated: String
    @SerializedName("phoneNumber") lateinit var phoneNumber: String
    @SerializedName("name") lateinit var userName: String
    @SerializedName("avatar") lateinit var avatar: String
    @SerializedName("team") lateinit var team: Team
}