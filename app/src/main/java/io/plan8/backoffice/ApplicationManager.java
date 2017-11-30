package io.plan8.backoffice;

import java.util.List;

import io.plan8.backoffice.model.api.Me;
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.model.api.Team;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ApplicationManager {
    private static volatile ApplicationManager instance = null;
    private Me me;
    private List<Team> teams;
    private List<Reservation> reservations;

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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
