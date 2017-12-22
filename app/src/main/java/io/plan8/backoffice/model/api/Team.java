package io.plan8.backoffice.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Team implements BaseModel {
    @SerializedName("id")
    @Expose()
    int teamId;
    @SerializedName("publicId")
    @Expose()
    String publicId;
    @SerializedName("name")
    @Expose()
    String name;
    @SerializedName("logo")
    @Expose()
    String logo;
    @SerializedName("configuration")
    @Expose()
    Configuration configuration;
    @SerializedName("ownerId")
    @Expose()
    String ownerId;
    @SerializedName("deactivated")
    @Expose()
    boolean deactivated;

    public Team() {
    }

    public int getTeamId() {
        return teamId;
    }

    public String getPublicId() {
        return publicId;
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
