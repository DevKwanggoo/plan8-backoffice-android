package io.plan8.backoffice.vm.item;

import android.content.Intent;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.MainActivity;
import io.plan8.backoffice.model.api.Team;
import io.plan8.backoffice.vm.FragmentVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class MoreTeamItemVM extends FragmentVM {
    private Team team;

    public MoreTeamItemVM(Fragment fragment, Bundle savedInstanceState, Team team) {
        super(fragment, savedInstanceState);
        this.team = team;
    }

    @Bindable
    public String getTeamName() {
        if (null == team) {
            return "";
        }
        return team.getName();
    }

    @Bindable
    public String getTeamDescription() {
        if (null == team) {
            return "";
        }
        return team.getName();
    }

    @Bindable
    public boolean getSelectTeamFlag() {
        return ApplicationManager.getInstance().getCurrentTeam().getTeamId() == team.getTeamId();
    }

    public void selectTeam(View view) {
        if (ApplicationManager.getInstance().getCurrentTeam().getTeamId() == team.getTeamId()){
            Toast.makeText(getFragment().getContext(), "현재 선택되어 있는 팀입니다.", Toast.LENGTH_SHORT).show();
        } else {
            ApplicationManager.getInstance().setCurrentTeam(team);

            Intent mainIntent = MainActivity.buildIntent(getFragment().getContext());
            getFragment().getActivity().startActivity(mainIntent);
            getFragment().getActivity().finish();
            getFragment().getActivity().overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
        }
    }

    @Bindable
    public String getTeamLogo(){
        if (null != team.getLogo()){
            return team.getLogo();
        }
        return "";
    }
}