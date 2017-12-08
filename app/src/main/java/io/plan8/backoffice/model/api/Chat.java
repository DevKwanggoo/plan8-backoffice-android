package io.plan8.backoffice.model.api;

import com.google.gson.annotations.SerializedName;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by chokwanghwan on 2017. 12. 8..
 */

public class Chat implements BaseModel {
    //chat
    @SerializedName("author") String author;
    @SerializedName("comment") String comment;
    @SerializedName("created") String created;

    // file
    private String name;
    private String mimeType;
    private String createdDate;
    private String fileName;
    private String imageUrl;
    private String authAvatar;
}