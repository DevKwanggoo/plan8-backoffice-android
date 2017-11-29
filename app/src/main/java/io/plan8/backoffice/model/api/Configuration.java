package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Configuration implements BaseModel {
    @SerializedName("id") String configId;
    @SerializedName("enable") boolean enable;
    @SerializedName("businessHours") BusinessHours businessHours;
    @SerializedName("durationOfTemporaryReservation") String durationOfTemporaryReservation;
    @SerializedName("timeSlotSize") String timeSlotSize;
    @SerializedName("cancelableHours") String cancelableHours;
    @SerializedName("reservableDays") String reservableDays;
    @SerializedName("marginLeftMinutes") String marginLeftMinutes;
    @SerializedName("marginRightMinutes") String marginRightMinutes;
    @SerializedName("transportation") String transportation;
    @SerializedName("startingPoint") StartingPoint startingPoint;
    @SerializedName("addressRestrictionRules") String addressRestrictionRules;
    @SerializedName("teamId") String teamId;

    private class StartingPoint implements BaseModel {
        @SerializedName("id") String id;
        @SerializedName("address") String address;
        @SerializedName("latitude") long latitude;
        @SerializedName("longitude") long longitude;
    }

    public Configuration() {
    }

    public String getConfigId() {
        return configId;
    }

    public boolean isEnable() {
        return enable;
    }

    public BusinessHours getBusinessHours() {
        return businessHours;
    }

    public String getDurationOfTemporaryReservation() {
        return durationOfTemporaryReservation;
    }

    public String getTimeSlotSize() {
        return timeSlotSize;
    }

    public String getCancelableHours() {
        return cancelableHours;
    }

    public String getReservableDays() {
        return reservableDays;
    }

    public String getMarginLeftMinutes() {
        return marginLeftMinutes;
    }

    public String getMarginRightMinutes() {
        return marginRightMinutes;
    }

    public String getTransportation() {
        return transportation;
    }

    public StartingPoint getStartingPoint() {
        return startingPoint;
    }

    public String getAddressRestrictionRules() {
        return addressRestrictionRules;
    }

    public String getTeamId() {
        return teamId;
    }
}
