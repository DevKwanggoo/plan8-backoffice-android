package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class Notification implements BaseModel {
    @SerializedName("action") Action action;
    @SerializedName("id") int id;
    @SerializedName("created") String created;
    @SerializedName("updated") String updated;
    @SerializedName("read") boolean read;

    public Notification() {
    }

    public Action getAction() {
        return action;
    }

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }



    public String getUpdated() {
        return updated;
    }

    public boolean isRead() {
        return read;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
