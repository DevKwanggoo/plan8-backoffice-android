package io.plan8.backoffice;

import android.content.Context;
import android.os.Build;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.plan8.backoffice.activity.MainActivity;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.model.api.Team;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.util.PushManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ApplicationManager {
    private Context context;
    private static volatile ApplicationManager instance = null;
    private User user;
    private List<Member> members;
    private List<Member> currentTeamMembers;
    private Member currentMember;
    private Team currentTeam;
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
        return "https://api-sandbox.plan8.io";
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

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
        if (null != currentTeam) {
            final Call<List<Member>> currentWorkersCall = RestfulAdapter.getInstance().getServiceApi().getCurrentTeamMemebers("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getContext()), currentTeam.getTeamId());
            currentWorkersCall.enqueue(new Callback<List<Member>>() {
                @Override
                public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                    currentTeamMembers = response.body();
                }

                @Override
                public void onFailure(Call<List<Member>> call, Throwable t) {

                }
            });

        }
    }

    public Member getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(Member currentMember) {
        setCurrentTeam(currentMember.getTeam());
        this.currentMember = currentMember;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public List<Member> getCurrentTeamMembers() {
        return currentTeamMembers;
    }

    public void setCurrentTeamMembers(List<Member> currentTeamMembers) {
        this.currentTeamMembers = currentTeamMembers;
    }

    public static void setInstance(ApplicationManager instance) {
        ApplicationManager.instance = instance;
    }

    public String getServerTimeOffset() {
        return serverTimeOffset;
    }

    public void setServerTimeOffset(String serverTimeOffset) {
        this.serverTimeOffset = serverTimeOffset;
    }
}
