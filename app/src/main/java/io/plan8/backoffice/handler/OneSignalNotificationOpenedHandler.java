package io.plan8.backoffice.handler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

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
        Uri openUrl = null;
        try {
            if (data.getString("openUrl") != null) {
                openUrl = Uri.parse(data.getString("openUrl"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String customKey;

        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.e("OneSignalExample", "customkey set with value: " + customKey);
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.e("OneSignalExample", "Button pressed with id: " + result.action.actionID);

        if (openUrl != null) {
            Intent detailTaskIntent = DetailReservationActivity.buildIntent(context, openUrl.toString(), true);
            detailTaskIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(detailTaskIntent);
        }
    }
}
