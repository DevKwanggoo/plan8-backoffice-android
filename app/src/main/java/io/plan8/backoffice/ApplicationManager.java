package io.plan8.backoffice;

import io.plan8.backoffice.model.api.Me;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ApplicationManager {
    private static volatile ApplicationManager instance = null;
    private Me me;

    public static ApplicationManager getInstance() {
        if (null == instance) {
            synchronized (ApplicationManager.class) {
                instance = new ApplicationManager();
            }
        }
        return instance;
    }

    public String getServerUrl() {
        return "https://api-sandbox.plan8.io";
    }

    public Me getMe() {
        return me;
    }

    public void setMe(Me me) {
        this.me = me;
    }
}
