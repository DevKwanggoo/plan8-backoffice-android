package io.plan8.backoffice.util;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import io.plan8.backoffice.ApplicationManager;

/**
 * Created by chokwanghwan on 2017. 12. 12..
 */

public class OneSignalNotificationReciever implements OneSignal.NotificationReceivedHandler {
    @Override
    public void notificationReceived(OSNotification notification) {
        ApplicationManager.getInstance().refreshNotificationCount();
        //refresh notification fragment
        if (null != ApplicationManager.getInstance().getMainActivity()
                && null != ApplicationManager.getInstance().getMainActivity().getNotificationFragment()) {
            ApplicationManager.getInstance().getMainActivity().getNotificationFragment().refreshNotificationList();
        }
    }
}
