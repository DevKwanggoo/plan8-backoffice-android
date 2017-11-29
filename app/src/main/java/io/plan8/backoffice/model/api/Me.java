package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Me implements BaseModel {
    @SerializedName("id") String id;
    @SerializedName("created") String created;
    @SerializedName("updated") String updated;
    @SerializedName("phoneNumber") String phoneNumber;
    @SerializedName("name") String name;
    @SerializedName("avatar") String avatar;
    @SerializedName("memberships") List<Team> teams;

    public Me() {
    }

    public String getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
