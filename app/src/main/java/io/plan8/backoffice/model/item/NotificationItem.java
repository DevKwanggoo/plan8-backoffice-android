package io.plan8.backoffice.model.item;

import io.plan8.backoffice.model.BaseModel;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class NotificationItem implements BaseModel {
    private String avatar;
    private String description;
    private String lastModified;

    public NotificationItem(String avatar, String description, String lastModified) {
        this.avatar = avatar;
        this.description = description;
        this.lastModified = lastModified;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
}
