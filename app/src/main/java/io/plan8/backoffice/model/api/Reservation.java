package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class Reservation implements BaseModel {
    @SerializedName("id") int id;
    @SerializedName("start") String start;
    @SerializedName("end") String end;
    @SerializedName("totalPrice") int totalPrice;
    @SerializedName("address") Address address;
    @SerializedName("additionalRequests") String addtionalRequest;
    @SerializedName("status") String status;
    @SerializedName("mobileNumber") String mobileNumber;
    @SerializedName("email") String email;
    @SerializedName("user") User user;
    @SerializedName("userId") int userId;

    public int getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Address getAddress() {
        return address;
    }

    public String getAddtionalRequest() {
        return addtionalRequest;
    }

    public String getStatus() {
        return status;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public User getUser() {
        return user;
    }

    public int getUserId() {
        return userId;
    }
}
