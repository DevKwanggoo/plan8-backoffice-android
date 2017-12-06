package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Shifts implements BaseModel {
    @SerializedName("monday") Week monday;
    @SerializedName("tuesday") Week tuesday;
    @SerializedName("wednesday") Week wednesday;
    @SerializedName("thursday") Week thursday;
    @SerializedName("friday") Week friday;
    @SerializedName("saturday") Week saturday;
    @SerializedName("sunday") Week sunday;
    @SerializedName("morningTime") Week morningTime;
    @SerializedName("lunchTime") Week lunchTime;
    @SerializedName("dinnerTime") Week dinnerTime;

    private class Week implements BaseModel{
        @SerializedName("on") boolean on;
        @SerializedName("startMinuted") int startMinuted;
        @SerializedName("endMinuted") int endMinuted;

        public Week() {
        }

        public boolean isOn() {
            return on;
        }

        public void setOn(boolean on) {
            this.on = on;
        }

        public int getStartMinuted() {
            return startMinuted;
        }

        public void setStartMinuted(int startMinuted) {
            this.startMinuted = startMinuted;
        }

        public int getEndMinuted() {
            return endMinuted;
        }

        public void setEndMinuted(int endMinuted) {
            this.endMinuted = endMinuted;
        }
    }

    public Shifts() {
    }

    public Week getMonday() {
        return monday;
    }

    public Week getTuesday() {
        return tuesday;
    }

    public Week getWednesday() {
        return wednesday;
    }

    public Week getThursday() {
        return thursday;
    }

    public Week getFriday() {
        return friday;
    }

    public Week getSaturday() {
        return saturday;
    }

    public Week getSunday() {
        return sunday;
    }
}
