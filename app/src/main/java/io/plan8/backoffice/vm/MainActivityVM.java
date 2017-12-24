package io.plan8.backoffice.vm;

import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.BR;
import io.plan8.backoffice.activity.BaseActivity;
import io.plan8.backoffice.activity.MainActivity;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class MainActivityVM extends ActivityVM {
    private boolean emptyTeamFlag = false;

    private MainActivity activity;

    public MainActivityVM(BaseActivity activity, Bundle savedInstanceState) {
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
        ApplicationManager.getInstance().logout();
    }

}
