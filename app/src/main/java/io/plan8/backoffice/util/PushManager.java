package io.plan8.backoffice.util;

import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import io.plan8.backoffice.model.api.User;

/**
 * Created by chokwanghwan on 2017. 12. 11..
 */

public class PushManager {
    public void setPublicIdTag(User user) {
        JSONObject tags = new JSONObject();
        try {
            if (null != user) {
                String publicId = user.getPublicId();
                tags.put("user", publicId);
                OneSignal.sendTags(tags);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void clearTag() {
        OneSignal.sendTags("");
    }

}
