package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class BusinessHours implements BaseModel {
    @SerializedName("monday") Week monday;
    @SerializedName("tuesday") Week tuesday;
    @SerializedName("wednesday") Week wednesday;
    @SerializedName("thursday") Week thursday;
    @SerializedName("friday") Week friday;
    @SerializedName("saturday") Week saturday;
    @SerializedName("sunday") Week sunday;

    private class Week implements BaseModel{
        @SerializedName("open") boolean isOpen;
        @SerializedName("startMinuted") int startMinuted;
        @SerializedName("endMinuted") int endMinuted;
    }

    public BusinessHours() {
    }

    public Week getMonday() {
        return monday;
    }

    public Week getTuesday() {
        return tuesday;
    }

    public Week getWednesday() {
        return wednesday;
    }

    public Week getThursday() {
        return thursday;
    }

    public Week getFriday() {
        return friday;
    }

    public Week getSaturday() {
        return saturday;
    }

    public Week getSunday() {
        return sunday;
    }
}
