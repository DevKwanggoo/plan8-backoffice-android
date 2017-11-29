package io.plan8.backoffice.model.item;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class Comment implements BaseModel {
    @SerializedName("author") String author;
    @SerializedName("comment") String comment;
    @SerializedName("created") String created;

    public Comment(String author, String comment, String created) {
        this.author = author;
        this.comment = comment;
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}