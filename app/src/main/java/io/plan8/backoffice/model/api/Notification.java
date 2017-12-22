package io.plan8.backoffice.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class Notification implements BaseModel {
    @SerializedName("action")
    @Expose()
    Action action;
    @SerializedName("id")
    @Expose()
    int id;
    @SerializedName("added")
    @Expose()
    String added;
    @SerializedName("edited")
    @Expose()
    String edited;
    @SerializedName("read")
    @Expose()
    boolean read;

    public Notification() {
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
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

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
