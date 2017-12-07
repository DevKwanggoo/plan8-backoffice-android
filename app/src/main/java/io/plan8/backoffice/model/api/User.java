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
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class User implements BaseModel {
    @SerializedName("mobileNumber")
    String mobileNumber;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("id")
    String userId;
    @SerializedName("updated")
    String updated;
    @SerializedName("created")
    String created;
    @SerializedName("username")
    String userName;
    @SerializedName("name")
    String name;
    @SerializedName("hasPassword")
    boolean hasPassword;

    public User() {
    }

    public User(String name, String userName, String avatar) {
        this.name = name;
        this.userName = userName;
        this.avatar = avatar;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUserId() {
        return userId;
    }

    public String getUpdated() {
        return updated;
    }

    public String getCreated() {
        return created;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public boolean isHasPassword() {
        return hasPassword;
    }
}