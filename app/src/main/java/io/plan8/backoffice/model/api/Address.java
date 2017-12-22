package io.plan8.backoffice.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class Address implements BaseModel {
    @SerializedName("name")
    @Expose()
    String name;

    @SerializedName("alias")
    @Expose()
    String alias;

    @SerializedName("latitude")
    @Expose()
    Number latitude;

    @SerializedName("longitude")
    @Expose()
    Number longitude;

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public Number getLatitude() {
        return latitude;
    }

    public Number getLongitude() {
        return longitude;
    }
}
