package io.plan8.backoffice.model.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.linkedin.android.spyglass.mentions.Mentionable;
import com.linkedin.android.spyglass.tokenization.QueryToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.util.MentionsLoader;

/**
 * Created by SSozi on 2017. 12. 7..
 */

public class Member implements BaseModel {
    @SerializedName("id")
    int id;
    @SerializedName("created")
    String created;
    @SerializedName("updated")
    String updated;
    @SerializedName("configuration")
    Configuration configuration;
    @SerializedName("user")
    User user;
    @SerializedName("phoneNumber")
    String phoneNumber;
    @SerializedName("owner")
    boolean owner;
    @SerializedName("admin")
    boolean admin;
    @SerializedName("team")
    Team team;
    @SerializedName("deactivated")
    boolean deactivated;
    @SerializedName("daysOff")
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