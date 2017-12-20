package io.plan8.backoffice;

import android.content.Context;
import android.os.Build;

import java.util.List;
import java.util.Locale;

import io.plan8.backoffice.activity.MainActivity;
import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.util.PushManager;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ApplicationManager {
    private static final String BASE_SERVER_URL = "https://api-sandbox.plan8.io";
//    private static final String BASE_SERVER_URL = "http://192.168.1.207:3000";
    private Context context;
    private static volatile ApplicationManager instance = null;
    private User user;
    private List<Member> members;
    private MainActivity mainActivity;
    private String serverTimeOffset;

    public static ApplicationManager getInstance() {
        if (null == instance) {
            synchronized (ApplicationManager.class) {
                instance = new ApplicationManager();
            }
        }
        return instance;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            return context.getResources().getConfiguration().locale;
        }
    }

    public String getServerUrl() {
        return BASE_SERVER_URL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        new PushManager().setPublicIdTag(user);
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public String getServerTimeOffset() {
        return serverTimeOffset;
    }

    public void setServerTimeOffset(String serverTimeOffset) {
        this.serverTimeOffset = serverTimeOffset;
    }
}
