package io.plan8.backoffice.handler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import io.plan8.backoffice.Constants;
import io.plan8.backoffice.activity.DetailTaskActivity;
import io.plan8.backoffice.model.item.TaskItem;

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
        String customKey;

        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.e("OneSignalExample", "customkey set with value: " + customKey);
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.e("OneSignalExample", "Button pressed with id: " + result.action.actionID);

        Intent detailTaskIntent = DetailTaskActivity.buildIntent(context, new TaskItem("김형규", "01065117399", "서울시 중구", "2017-12-12", "오후 12:00", "오후 1:00", "아이폰", "빨리와주세요", "디스크립션", Constants.TASK_STATUS_BLUE, ""));
        detailTaskIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(detailTaskIntent);
    }
}
