package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 12. 14..
 */

public class ServerTime implements BaseModel {
    @SerializedName("offset") private String offset;
    @SerializedName("server") private String server;
    @SerializedName("client") private String client;

    public ServerTime () {}

    public String getOffset() {
        return offset;
    }

    public String getServer() {
        return server;
    }

    public String getClient() {
        return client;
    }
}
