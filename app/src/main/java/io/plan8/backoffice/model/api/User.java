package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class User implements BaseModel {
    @SerializedName("phoneNumber") String phoneNumber;
    @SerializedName("avatar") String avatar;
    @SerializedName("id") String userId;
    @SerializedName("updated") String updated;
    @SerializedName("created") String created;
    @SerializedName("username") String userName;
    @SerializedName("hasPassword") boolean hasPassword;

    public User() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public boolean isHasPassword() {
        return hasPassword;
    }
}
