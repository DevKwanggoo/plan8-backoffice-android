package io.plan8.backoffice.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class NetworkUtil {
    private static volatile NetworkUtil instance = null;

    public static NetworkUtil getInstance() {
        if (null == instance) {
            synchronized (NetworkUtil.class) {
                instance = new NetworkUtil();
            }
        }
        return instance;
    }

    public boolean checkInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean result = false;
        if (null != connectivityManager
                && null != connectivityManager.getActiveNetworkInfo()
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected()) {
            result = true;
        }
        return result;
    }
}
