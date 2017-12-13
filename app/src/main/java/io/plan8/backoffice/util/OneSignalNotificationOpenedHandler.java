package io.plan8.backoffice.util;

import android.content.Context;
import android.content.Intent;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import io.plan8.backoffice.activity.DetailReservationActivity;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class OneSignalNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    private Context context;

    public OneSignalNotificationOpenedHandler(Context context) {
        this.context = context;
    }

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        int reservationId = -1;
        int notificationId = -1;
        try {
            reservationId = data.getInt("reservationId");
            notificationId = data.getInt("notificationId");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (reservationId != -1 && notificationId != -1) {
            Intent detailTaskIntent = DetailReservationActivity.buildIntent(context, reservationId, notificationId);
            detailTaskIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(detailTaskIntent);
        }
    }
}