package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class Reservation implements BaseModel {
    @SerializedName("id") int id;
    @SerializedName("name") int name;
    @SerializedName("added") String added;
    @SerializedName("edited") String edited;
    @SerializedName("start") String start;
    @SerializedName("end") String end;
    @SerializedName("value") int value;
    @SerializedName("additionalRequests") String addtionalRequest;
    @SerializedName("status") String status;
    @SerializedName("expires") String expires;
    @SerializedName("phoneNumber") String phoneNumber;
    @SerializedName("email") String email;
    @SerializedName("address") Address address;
    @SerializedName("products") List<Product> products;
    @SerializedName("workers") List<Member> workers;
    @SerializedName("user") User user;
    @SerializedName("userId") int userId;
    @SerializedName("team") Team team;

    public Reservation() {
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
