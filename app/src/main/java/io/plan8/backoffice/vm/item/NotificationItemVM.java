package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import io.plan8.backoffice.activity.DetailReservationActivity;
import io.plan8.backoffice.model.api.Notification;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.util.ViewUtil;
import io.plan8.backoffice.vm.FragmentVM;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class NotificationItemVM extends FragmentVM {
    private Notification notification;

    public NotificationItemVM(Fragment fragment, Bundle savedInstanceState, Notification notification) {
        super(fragment, savedInstanceState);
        this.notification = notification;
    }

    @Bindable
    public String getText() {
        if (null == notification || null == notification.getAction()) {
            return "";
        }
        String text = "";
        text = ViewUtil.getInstance().getActionItemText(notification.getAction()) + " ";
        if (null != notification.getAction().getText()
                && notification.getAction().getText().length() >= 75) {
            text += notification.getAction().getText().substring(0, 75) + "...";
        } else {
            text += notification.getAction().getText();
        }

        return text;
    }

    @Bindable
    public String getAvatar() {
        if (null == notification || null == notification.getAction() || null == notification.getAction().getCreator()) {
            return "";
        }
        return notification.getAction().getCreator().getAvatar();
    }

    @Bindable
    public String getLastModified() {
        if (null == notification) {
            return "";
        }

        return DateUtil.getInstance().getChatTime(notification.getCreated());
    }

    public void detailNotification(View view) {
        if (null == notification || null == notification.getAction()) {
            return;
        }

        getFragment().getActivity().startActivity(DetailReservationActivity.buildIntent(getFragment().getContext(), notification.getAction().getReservation().getId(), notification.getId()));
        //TODO : 읽음 처리 한다.
    }

//    @Bindable
//    public boolean isRead() {
//
//    }
}
