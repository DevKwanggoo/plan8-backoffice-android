package io.plan8.backoffice.util;

import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import io.plan8.backoffice.model.api.Me;

/**
 * Created by chokwanghwan on 2017. 12. 11..
 */

public class PushManager {
    public void setPublicIdTag(Me me) {
        JSONObject tags = new JSONObject();
        try {
            if (null != me) {
                String publicId = me.getPublicId();
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
