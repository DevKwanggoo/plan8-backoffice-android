package io.plan8.backoffice;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class SharedPreferenceManager {
    private static volatile SharedPreferenceManager instance = null;

    public static SharedPreferenceManager getInstance() {
        if (null == instance) {
            synchronized (SharedPreferenceManager.class) {
                instance = new SharedPreferenceManager();
            }
        }
        return instance;
    }

    public String getUserToken(Context context) {
        return context.getSharedPreferences("plan8", Context.MODE_PRIVATE).getString("token", "");
    }

    public void setUserToken(Context context, String userToken) {
        SharedPreferences.Editor editor = context.getSharedPreferences("plan8", Context.MODE_PRIVATE).edit();
        editor.putString("token", userToken);
        editor.apply();
    }

    public void clearUserToken(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("plan8", Context.MODE_PRIVATE).edit();
        editor.putString("token", "");
        editor.apply();
    }

    public void setIsFirstInstalled(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("plan8", Context.MODE_PRIVATE).edit();
        editor.putBoolean("isFirstInstalled", true);
        editor.apply();
    }
}
