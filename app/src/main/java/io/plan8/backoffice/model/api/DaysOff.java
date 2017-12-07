package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 12. 7..
 */

public class DaysOff implements BaseModel {
    @SerializedName("id") int id;
    @SerializedName("name") String name;
    @SerializedName("start") String start;
    @SerializedName("end") String end;

    public DaysOff() {
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
