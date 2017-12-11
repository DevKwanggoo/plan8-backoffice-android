package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Me implements BaseModel {
    @SerializedName("id")
    int id;
    @SerializedName("created")
    String created;
    @SerializedName("updated")
    String updated;
    @SerializedName("mobileNumber")
    String mobileNumber;
    @SerializedName("name")
    String name;
    @SerializedName("username")
    String username;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("publicId")
    String publicId;
    @SerializedName("verified")
    boolean verified;
    @SerializedName("hasPassword")
    boolean hasPassword;
    @SerializedName("hasName")
    boolean hasName;
    @SerializedName("memberships")
    List<Team> teams;

    public Me() {
    }

    public String getPublicId() {
        return publicId;
    }

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
