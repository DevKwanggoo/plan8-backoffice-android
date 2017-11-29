package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Auth implements BaseModel {
    @SerializedName("user") User user;
    @SerializedName("token") String token;

    public Auth() {
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}