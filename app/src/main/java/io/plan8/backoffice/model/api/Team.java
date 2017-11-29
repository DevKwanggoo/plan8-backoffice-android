package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Team implements BaseModel {
    @SerializedName("id") String teamId;
    @SerializedName("name") String name;
    @SerializedName("logo") String logo;
    @SerializedName("configuration") Configuration configuration;
    @SerializedName("ownerId") String ownerId;
    @SerializedName("deactivated") boolean deactivated;

    public Team() {
    }

    public String getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public boolean isDeactivated() {
        return deactivated;
    }
}
