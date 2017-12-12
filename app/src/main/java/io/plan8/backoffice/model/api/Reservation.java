package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class Reservation implements BaseModel {
    @SerializedName("id") int id;
    @SerializedName("created") String created;
    @SerializedName("updated") String updated;
    @SerializedName("start") String start;
    @SerializedName("end") String end;
    @SerializedName("totalPrice") int totalPrice;
    @SerializedName("additionalRequests") String addtionalRequest;
    @SerializedName("status") String status;
    @SerializedName("phoneNumber") String phoneNumber;
    @SerializedName("email") String email;
    @SerializedName("address") Address address;
    @SerializedName("products") List<Product> products;
    @SerializedName("workers") List<Member> workers;
    @SerializedName("user") User user;
    @SerializedName("userId") int userId;

    private boolean myReservation;

    public Reservation() {
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddtionalRequest() {
        return addtionalRequest;
    }

    public void setAddtionalRequest(String addtionalRequest) {
        this.addtionalRequest = addtionalRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Member> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Member> workers) {
        this.workers = workers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isMyReservation() {
        return myReservation;
    }

    public void setMyReservation(boolean myReservation) {
        this.myReservation = myReservation;
    }
}
