package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 12. 7..
 */

public class Member implements BaseModel {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("username")
    String username;
    @SerializedName("mobileNumber")
    String mobileNumber;
    @SerializedName("email")
    String email;
    @SerializedName("owner")
    boolean owner;
    @SerializedName("admin")
    boolean admin;
    @SerializedName("daysOff")
    DaysOff daysOff;
    @SerializedName("configuration")
    Configuration configuration;
    @SerializedName("deactivated")
    boolean deactivated;

    public Member() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public DaysOff getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(DaysOff daysOff) {
        this.daysOff = daysOff;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public void setDeactivated(boolean deactivated) {
        this.deactivated = deactivated;
    }
}
