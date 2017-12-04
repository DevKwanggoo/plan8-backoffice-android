package io.plan8.backoffice.model.item;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 11. 29..
 */

public class CommentReplaceItem implements BaseModel {
    private String userName;
    private String replacePoint;
    private String createdDate;

    public CommentReplaceItem(String userName, String replacePoint, String createdDate) {
        this.userName = userName;
        this.replacePoint = replacePoint;
        this.createdDate = createdDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReplacePoint() {
        return replacePoint;
    }

    public void setReplacePoint(String replacePoint) {
        this.replacePoint = replacePoint;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
