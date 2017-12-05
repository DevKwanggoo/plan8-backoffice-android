package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.plan8.backoffice.model.item.NotificationItem;
import io.plan8.backoffice.vm.FragmentVM;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class NotificationItemVM extends FragmentVM {
    private NotificationItem notificationItem;
    public NotificationItemVM(Fragment fragment, Bundle savedInstanceState, NotificationItem notificationItem) {
        super(fragment, savedInstanceState);
        this.notificationItem = notificationItem;
    }

    @Bindable
    public String getDescription(){
        if (notificationItem != null && notificationItem.getDescription() != null){
            return notificationItem.getDescription();
        }
        return "";
    }

    @Bindable
    public String getAvatar() {
        if (notificationItem != null && notificationItem.getAvatar() != null){
            return notificationItem.getAvatar();
        }
        return "";
    }

    @Bindable
    public String getLastModified(){
        if (notificationItem != null && notificationItem.getLastModified() != null){
            return notificationItem.getLastModified();
        }
        return "";
    }
}
