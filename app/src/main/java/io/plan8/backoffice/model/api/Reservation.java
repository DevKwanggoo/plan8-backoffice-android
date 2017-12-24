package io.plan8.backoffice.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class Reservation implements BaseModel {
    @SerializedName("id")
    @Expose()
    int id;
    @SerializedName("name")
    @Expose()
    int name;
    @SerializedName("added")
    @Expose()
    String added;
    @SerializedName("edited")
    @Expose()
    String edited;
    @SerializedName("start")
    @Expose()
    String start;
    @SerializedName("end")
    @Expose()
    String end;
    @SerializedName("value")
    @Expose()
    int value;
    @SerializedName("additionalRequests")
    @Expose()
    String addtionalRequest;
    @SerializedName("status")
    @Expose()
    String status;
    @SerializedName("expires")
    @Expose()
    String expires;
    @SerializedName("phoneNumber")
    @Expose()
    String phoneNumber;
    @SerializedName("email")
    @Expose()
    String email;
    @SerializedName("address")
    @Expose()
    Address address;
    @SerializedName("product")
    @Expose()
    Product product;
    @SerializedName("worker")
    @Expose()
    Member worker;
    @SerializedName("user")
    @Expose()
    User user;
    @SerializedName("userId")
    @Expose()
    int userId;
    @SerializedName("team")
    @Expose()
    Team team;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Member getWorker() {
        return worker;
    }

    public void setWorker(Member worker) {
        this.worker = worker;
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
