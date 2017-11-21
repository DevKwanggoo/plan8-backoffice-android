package io.plan8.backoffice.model.api

import com.google.gson.annotations.SerializedName
import io.plan8.backoffice.model.BaseModel

/**
 * Created by SSozi on 2017. 11. 20..
 */
class BusinessHours : BaseModel {
    @SerializedName("monday") lateinit var monday: Monday
    @SerializedName("tuesday") lateinit var tuesday: Tuesday
    @SerializedName("wednesday") lateinit var wednesday: WednesDay
    @SerializedName("thursday") lateinit var thursday: Thursday
    @SerializedName("friday") lateinit var friday: Friday
    @SerializedName("saturday") lateinit var saturday: Saturday
    @SerializedName("sunday") lateinit var sunday: Sunday

    class Monday : BaseModel {
        @SerializedName("open")
        var isOpen: Boolean = true
        @SerializedName("startMinuted")
        var startMinuted: Int = 0
        @SerializedName("endMinuted")
        var endMinuted: Int = 0
    }

    class Tuesday : BaseModel {
        @SerializedName("open")
        var isOpen: Boolean = true
        @SerializedName("startMinuted")
        var startMinuted: Int = 0
        @SerializedName("endMinuted")
        var endMinuted: Int = 0
    }

    class WednesDay : BaseModel {
        @SerializedName("open")
        var isOpen: Boolean = true
        @SerializedName("startMinuted")
        var startMinuted: Int = 0
        @SerializedName("endMinuted")
        var endMinuted: Int = 0
    }

    class Thursday : BaseModel {
        @SerializedName("open")
        var isOpen: Boolean = true
        @SerializedName("startMinuted")
        var startMinuted: Int = 0
        @SerializedName("endMinuted")
        var endMinuted: Int = 0
    }

    class Friday : BaseModel {
        @SerializedName("open")
        var isOpen: Boolean = true
        @SerializedName("startMinuted")
        var startMinuted: Int = 0
        @SerializedName("endMinuted")
        var endMinuted: Int = 0
    }

    class Saturday : BaseModel {
        @SerializedName("open")
        var isOpen: Boolean = true
        @SerializedName("startMinuted")
        var startMinuted: Int = 0
        @SerializedName("endMinuted")
        var endMinuted: Int = 0
    }

    class Sunday : BaseModel {
        @SerializedName("open")
        var isOpen: Boolean = true
        @SerializedName("startMinuted")
        var startMinuted: Int = 0
        @SerializedName("endMinuted")
        var endMinuted: Int = 0
    }
}