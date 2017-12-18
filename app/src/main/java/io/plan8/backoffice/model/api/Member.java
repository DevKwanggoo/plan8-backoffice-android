package io.plan8.backoffice.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 12. 7..
 */

public class Member implements BaseModel {
    @SerializedName("id")
    @Expose()
    int id;
    @SerializedName("created")
    @Expose()
    String created;
    @SerializedName("updated")
    @Expose()
    String updated;
    @SerializedName("configuration")
    @Expose()
    Configuration configuration;
    @SerializedName("user")
    @Expose()
    User user;
    @SerializedName("phoneNumber")
    @Expose()
    String phoneNumber;
    @SerializedName("owner")
    @Expose()
    boolean owner;
    @SerializedName("admin")
    @Expose()
    boolean admin;
    @SerializedName("team")
    @Expose()
    Team team;
    @SerializedName("deactivated")
    @Expose()
    boolean deactivated;
    @SerializedName("daysOff")
    @Expose()
    List<DayOff> dayOffs;

    public Member() {
    }

    public List<DayOff> getDayOffs() {
        return dayOffs;
    }

    public Team getTeam() {
        return team;
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

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public boolean isOwner() {
        return owner;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isDeactivated() {
        return deactivated;
    }


    public User getUser() {
        return user;
    }
}