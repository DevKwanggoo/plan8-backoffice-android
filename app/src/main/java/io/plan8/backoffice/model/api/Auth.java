package io.plan8.backoffice.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Auth implements BaseModel {
    @SerializedName("token")
    @Expose()
    String token;

    public Auth() {
    }

    public String getToken() {
        return token;
    }
}