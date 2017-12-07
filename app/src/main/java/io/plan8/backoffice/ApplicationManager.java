package io.plan8.backoffice;

import android.content.Context;

import java.util.List;
import java.util.Locale;

import io.plan8.backoffice.model.api.Me;
import io.plan8.backoffice.model.api.Team;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ApplicationManager {
    private Context context;
    private static volatile ApplicationManager instance = null;
    private Me me;
    private List<Team> teams;
    private Team currentTeam;

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
        return context.getResources().getConfiguration().getLocales().get(0);
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
    }
}
