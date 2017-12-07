package io.plan8.backoffice;

import java.util.List;

import io.plan8.backoffice.activity.MainActivity;
import io.plan8.backoffice.model.api.Me;
import io.plan8.backoffice.model.api.Team;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ApplicationManager {
    private static volatile ApplicationManager instance = null;
    private Me me;
    private List<Team> teams;
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

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
