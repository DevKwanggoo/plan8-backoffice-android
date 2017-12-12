package io.plan8.backoffice.util;

import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

/**
 * Created by chokwanghwan on 2017. 12. 12..
 */

public class OneSignalNotificationReciever implements OneSignal.NotificationReceivedHandler {
    @Override
    public void notificationReceived(OSNotification notification) {
        Log.e("test", "test");
    }
}
