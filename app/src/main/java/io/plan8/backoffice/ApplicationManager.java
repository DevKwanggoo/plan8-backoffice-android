package io.plan8.backoffice;

import android.content.Context;
import android.os.Build;

import java.util.List;
import java.util.Locale;

import io.plan8.backoffice.activity.MainActivity;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.model.api.Me;
import io.plan8.backoffice.model.api.Worker;
import io.plan8.backoffice.model.api.Team;
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
    private Me me;
    private List<Team> teams;
    private List<Worker> currentTeamWorkers;
    private Team currentTeam;
    private MainActivity mainActivity;

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

    public Me getMe() {
        return me;
    }

    public void setMe(Me me) {
        this.me = me;
        new PushManager().setPublicIdTag(me);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
        if (null != currentTeam) {
            final Call<List<Worker>> currentWorkersCall = RestfulAdapter.getInstance().getServiceApi().getCurrentTeamMemebers("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getContext()), currentTeam.getTeamId());
            currentWorkersCall.enqueue(new Callback<List<Worker>>() {
                @Override
                public void onResponse(Call<List<Worker>> call, Response<List<Worker>> response) {
                    currentTeamWorkers = response.body();
                }

                @Override
                public void onFailure(Call<List<Worker>> call, Throwable t) {

                }
            });

        }
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public List<Worker> getCurrentTeamWorkers() {
        return currentTeamWorkers;
    }

    public void setCurrentTeamWorkers(List<Worker> currentTeamWorkers) {
        this.currentTeamWorkers = currentTeamWorkers;
    }
}
