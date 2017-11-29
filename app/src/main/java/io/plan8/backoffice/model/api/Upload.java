package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Upload implements BaseModel {
    @SerializedName("name") String name;
    @SerializedName("mimetype") String mimetype;
    @SerializedName("url") String url;

    public Upload() {
    }

    public String getName() {
        return name;
    }

    public String getMimetype() {
        return mimetype;
    }

    public String getUrl() {
        return url;
    }
}
