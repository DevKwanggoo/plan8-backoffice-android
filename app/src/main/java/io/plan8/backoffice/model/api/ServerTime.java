package io.plan8.backoffice.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 12. 14..
 */

public class ServerTime implements BaseModel {
    @SerializedName("offset")
    @Expose()
    private String offset;
    @SerializedName("server")
    @Expose()
    private String server;
    @SerializedName("client")
    @Expose()
    private String client;

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
