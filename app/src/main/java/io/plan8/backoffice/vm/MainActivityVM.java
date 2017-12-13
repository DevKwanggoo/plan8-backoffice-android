package io.plan8.backoffice.vm;

import android.app.Activity;
import android.content.Intent;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.activity.LoginActivity;
import io.plan8.backoffice.activity.MainActivity;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class MainActivityVM extends ActivityVM {
    private boolean emptyTeamFlag = false;
    private MainActivity activity;

    public MainActivityVM(Activity activity, Bundle savedInstanceState) {
        super(activity, savedInstanceState);
        this.activity = (MainActivity) activity;
    }

    @Bindable
    public boolean getEmptyTeamFlag() {
        return emptyTeamFlag;
    }

    public void setEmptyTeamFlag(boolean emptyTeamFlag) {
        this.emptyTeamFlag = emptyTeamFlag;
        notifyPropertyChanged(BR.emptyTeamFlag);
    }

    public void teamLogout(View view) {
        SharedPreferenceManager.getInstance().clearUserToken(getActivity());
        Intent loginIntent = new Intent(activity, LoginActivity.class);
        activity.startActivity(loginIntent);
        activity.finish();
        activity.overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
//        setEmptyTeamFlag(false);
    }
}
