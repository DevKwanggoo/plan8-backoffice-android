package io.plan8.backoffice.model.api

import com.google.gson.annotations.SerializedName
import io.plan8.backoffice.model.BaseModel

/**
 * Created by SSozi on 2017. 11. 20..
 */
class Configuration: BaseModel {
    @SerializedName("id") lateinit var configId: String
    @SerializedName("enable") var enable: Boolean = true
    @SerializedName("businessHours") lateinit var businessHours: BusinessHours
    @SerializedName("durationOfTemporaryReservation") lateinit var durationOfTemporaryReservation: String
    @SerializedName("timeSlotSize") lateinit var timeSlotSize: String
    @SerializedName("cancelableHours") lateinit var cancelableHours: String
    @SerializedName("reservableDays") lateinit var reservableDays: String
    @SerializedName("marginLeftMinutes") lateinit var marginLeftMinutes: String
    @SerializedName("marginRightMinutes") lateinit var marginRightMinutes: String
    @SerializedName("transportation") lateinit var transportation: String
    @SerializedName("startingPoint") lateinit var startingPoint: StartingPoint
    @SerializedName("addressRestrictionRules") lateinit var addressRestrictionRules: String
    @SerializedName("teamId") lateinit var teamId: String

    class StartingPoint : BaseModel {
        @SerializedName("id") lateinit var startingPointId: String
        @SerializedName("address") lateinit var address: String
        @SerializedName("latitude")
        var latitude: Long = 0
        @SerializedName("longitude")
        var longitude: Long = 0
    }
}