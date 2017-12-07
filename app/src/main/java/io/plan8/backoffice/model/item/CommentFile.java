package io.plan8.backoffice.model.item;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 11. 29..
 */

public class CommentFile implements BaseModel {
    private String name;
    private String mimeType;
    private String createdDate;
    private String fileName;
    private String imageUrl;
    private String authAvatar;

    public CommentFile(String name, String authAvatar, String mimeType, String createdDate, String fileName, String imageUrl) {
        this.name = name;
        this.authAvatar = authAvatar;
        this.mimeType = mimeType;
        this.createdDate = createdDate;
        this.fileName = fileName;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthAvatar() {
        return authAvatar;
    }

    public void setAuthAvatar(String authAvatar) {
        this.authAvatar = authAvatar;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
