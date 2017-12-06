package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 12. 6..
 */

public class Product implements BaseModel {
    @SerializedName("id")
    int id;
    @SerializedName("created")
    String created;
    @SerializedName("updated")
    String updated;
    @SerializedName("name")
    String name;
    @SerializedName("description")
    String description;
    @SerializedName("duration")
    int duration;
    @SerializedName("price")
    int price;
    @SerializedName("numberOfWorkersRequired")
    int numberOfWorkersRequired;
    @SerializedName("deactivated")
    boolean deactivated;

    public Product() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberOfWorkersRequired() {
        return numberOfWorkersRequired;
    }

    public void setNumberOfWorkersRequired(int numberOfWorkersRequired) {
        this.numberOfWorkersRequired = numberOfWorkersRequired;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public void setDeactivated(boolean deactivated) {
        this.deactivated = deactivated;
    }
}
