package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 12. 6..
 */

public class Worker implements BaseModel {
    @SerializedName("id") int id;
    @SerializedName("created") String created;
    @SerializedName("updated") String updated;
    @SerializedName("configuration") Configuration configuration;
    @SerializedName("name") String name;
    @SerializedName("avatar") String avatar;
    @SerializedName("username") String username;
    @SerializedName("mobileNumber") String mobileNumber;
    @SerializedName("email") String email;
    @SerializedName("owner") boolean owner;
    @SerializedName("admin") boolean admin;
    @SerializedName("deactivated") boolean deactivated;

    public Worker() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public void setDeactivated(boolean deactivated) {
        this.deactivated = deactivated;
    }
}
