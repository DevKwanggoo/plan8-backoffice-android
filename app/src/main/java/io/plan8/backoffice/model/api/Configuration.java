package io.plan8.backoffice.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Configuration implements BaseModel {
    @SerializedName("transportation")
    @Expose()
    String transportation;
    @SerializedName("weight")
    @Expose()
    long weight;
    @SerializedName("assignable")
    @Expose()
    boolean assignable;
    @SerializedName("useTeamShifts")
    @Expose()
    boolean useTeamShifts;
    @SerializedName("useTeamTransportation")
    @Expose()
    boolean useTeamTransportation;
    @SerializedName("useTeamAddress")
    @Expose()
    boolean useTeamAddress;
    @SerializedName("useTeamWeight")
    @Expose()
    boolean useTeamWeight;
    @SerializedName("address")
    @Expose()
    Address address;

    public Configuration() {
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public boolean isAssignable() {
        return assignable;
    }

    public void setAssignable(boolean assignable) {
        this.assignable = assignable;
    }

    public boolean isUseTeamShifts() {
        return useTeamShifts;
    }

    public void setUseTeamShifts(boolean useTeamShifts) {
        this.useTeamShifts = useTeamShifts;
    }

    public boolean isUseTeamTransportation() {
        return useTeamTransportation;
    }

    public void setUseTeamTransportation(boolean useTeamTransportation) {
        this.useTeamTransportation = useTeamTransportation;
    }

    public boolean isUseTeamAddress() {
        return useTeamAddress;
    }

    public void setUseTeamAddress(boolean useTeamAddress) {
        this.useTeamAddress = useTeamAddress;
    }

    public boolean isUseTeamWeight() {
        return useTeamWeight;
    }

    public void setUseTeamWeight(boolean useTeamWeight) {
        this.useTeamWeight = useTeamWeight;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
