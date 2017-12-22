package io.plan8.backoffice.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class Action implements BaseModel {
    @SerializedName("id")
    @Expose(serialize = false)
    int id;

    @SerializedName("type")
    @Expose()
    String type;

    @SerializedName("creator")
    @Expose(serialize = false)
    User creator;

    @SerializedName("data")
    @Expose(serialize = false)
    Data data;

    @SerializedName("added")
    @Expose(serialize = false)
    String added;

    @SerializedName("edited")
    @Expose(serialize = false)
    String edited;

    @SerializedName("text")
    @Expose()
    String text;

    @SerializedName("attachment")
    @Expose()
    Attachment attachment;

    @SerializedName("reservation")
    @Expose()
    Reservation reservation;

    public Action() {
    }

    public Action(Reservation reservation, Attachment attachment) {
        this.type = "comment";
        this.reservation = reservation;
        this.attachment = attachment;
    }

    public Action(Reservation reservation, String text) {
        this.type = "comment";
        this.reservation = reservation;
        this.text = text;
    }

    public Action(String text) {
        this.text = text;
    }

    public class Data implements BaseModel {
        @SerializedName("before")
        @Expose
        String before;
        @SerializedName("after")
        @Expose
        String after;

        public Data() {
        }

        public String getBefore() {
            return before;
        }

        public String getAfter() {
            return after;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public User getCreator() {
        return creator;
    }
}