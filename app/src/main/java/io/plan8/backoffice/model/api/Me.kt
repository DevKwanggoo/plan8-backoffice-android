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
    @SerializedName("phoneNumber") var phoneNumber: String? = null
    @SerializedName("name") var userName: String? = null
    @SerializedName("avatar") lateinit var avatar: String
    @SerializedName("teams") lateinit var team: List<Team>
}