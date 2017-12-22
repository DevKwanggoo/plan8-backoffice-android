package io.plan8.backoffice.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 12. 7..
 */

public class DayOff implements BaseModel {
    @SerializedName("id")
    @Expose()
    private int id;
    @SerializedName("name")
    @Expose()
    private String name;
    @SerializedName("start")
    @Expose()
    private String start;
    @SerializedName("end")
    @Expose()
    private String end;

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
