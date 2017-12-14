package io.plan8.backoffice.model.api;

import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class Action implements BaseModel {
    @SerializedName("id") int id;
    @SerializedName("created") String created;
    @SerializedName("updated") String updated;
    @SerializedName("type") String type;
    @SerializedName("text") String text;
    @SerializedName("data") Data data;
    @SerializedName("attachment") Attachment attachment;
    @SerializedName("reservation") Reservation reservation;
    @SerializedName("creator") Creator creator;

    public Action() {
    }

    public Action(String text) {
        this.text = text;
    }

    public class Creator implements BaseModel {
        @SerializedName("id") int id;
        @SerializedName("username") String username;
        @SerializedName("name") String name;
        @SerializedName("avatar") String avatar;
        @SerializedName("hasPassword") boolean hasPassword;

        public Creator() {
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getName() {
            return name;
        }

        public String getAvatar() {
            return avatar;
        }

        public boolean isHasPassword() {
            return hasPassword;
        }
    }

    public class Data implements BaseModel {
        @SerializedName("before") String before;
        @SerializedName("after") String after;

        public Data() {
        }

        public String getBefore() {
            return before;
        }

        public String getAfter() {
            return after;
        }
    }

    public String getUpdated() {
        return updated;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Creator getCreator() {
        return creator;
    }

    public Data getData() {
        return data;
    }

    public String getCreated() {
        return created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}