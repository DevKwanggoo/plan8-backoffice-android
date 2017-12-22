package io.plan8.backoffice;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.plan8.backoffice.activity.MainActivity;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.util.PushManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private int notificationCount = 0;

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

    public int getNotificationCount() {
        return notificationCount;
    }

    public void refreshNotificationCount() {
        Call<ResponseBody> notificationCountCall = RestfulAdapter.getInstance().getServiceApi().getNotificationCount("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getContext()), false);
        notificationCountCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody result = response.body();
                if (result != null) {
                    try {
                        String requestJSON = result.string();
                        JSONObject jsonObject = new JSONObject(requestJSON);
                        String count = jsonObject.getString("value");

                        Log.e("notification count : ", count);

                        if (null == count || count.equals("") || Integer.parseInt(count) == 0){
                            setNotificationCount(0);
                            refreshAppBadgeCount(0);
                        } else {
                            setNotificationCount(Integer.parseInt(count));
                            refreshAppBadgeCount(Integer.parseInt(count));
                        }

                        if (null != mainActivity) {
                            mainActivity.refreshNotificationBadgeCount();
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "알림개수를 받아오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setNotificationCount(int notificationCount) {
        this.notificationCount = notificationCount;
    }

    public void refreshAppBadgeCount(int count) {
        Intent badgeIntent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        badgeIntent.putExtra("badge_count", count);
        badgeIntent.putExtra("badge_count_pakage_name", context.getPackageName());
        badgeIntent.putExtra("badge_count_class_name", "io.plan8.backoffice.activity.SplashActivity");
        context.sendBroadcast(badgeIntent);
    }
}
