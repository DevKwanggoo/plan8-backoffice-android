package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 12. 7..
 */

public class DayOff implements BaseModel {
    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("start") private String start;
    @SerializedName("end") private String end;

    public DayOff () {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
