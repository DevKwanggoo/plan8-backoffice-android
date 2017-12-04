package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Configuration implements BaseModel {
    @SerializedName("shifts") Shifts businessHours;
    @SerializedName("transportation") String transportation;
    @SerializedName("address") Address address;
    @SerializedName("weight") long weight;
    @SerializedName("id") int configId;
    @SerializedName("timeSlotSize") int timeSlotSize;
    @SerializedName("reservableDays") int reservableDays;
    @SerializedName("marginLeftMinutes") int marginLeftMinutes;
    @SerializedName("marginRightMinutes") int marginRightMinutes;


    // TODO : 이 아래는 모델에서 제거 됬지만 확정되기 전 까지 남겨놓음.
    @SerializedName("enable") boolean enable;
    @SerializedName("durationOfTemporaryReservation") String durationOfTemporaryReservation;
    @SerializedName("cancelableHours") String cancelableHours;
    @SerializedName("startingPoint") StartingPoint startingPoint;
    @SerializedName("addressRestrictionRules") String addressRestrictionRules;
    @SerializedName("teamId") String teamId;

    private class StartingPoint implements BaseModel {
        @SerializedName("id") String id;
        @SerializedName("address") String address;
        @SerializedName("latitude") long latitude;
        @SerializedName("longitude") long longitude;

        public String getId() {
            return id;
        }

        public String getAddress() {
            return address;
        }

        public long getLatitude() {
            return latitude;
        }

        public long getLongitude() {
            return longitude;
        }
    }

    public Configuration() {
    }

    public int getConfigId() {
        return configId;
    }

    public boolean isEnable() {
        return enable;
    }

    public Shifts getBusinessHours() {
        return businessHours;
    }

    public String getDurationOfTemporaryReservation() {
        return durationOfTemporaryReservation;
    }

    public int getTimeSlotSize() {
        return timeSlotSize;
    }

    public int getReservableDays() {
        return reservableDays;
    }

    public int getMarginLeftMinutes() {
        return marginLeftMinutes;
    }

    public int getMarginRightMinutes() {
        return marginRightMinutes;
    }

    public String getCancelableHours() {
        return cancelableHours;
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

    public Address getAddress() {
        return address;
    }

    public Long getWeight() {
        return weight;
    }
}
