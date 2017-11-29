package io.plan8.backoffice.vm;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.BR;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class MainActivityVM extends ActivityVM {
    private boolean emptyTeamFlag;
    public MainActivityVM(Activity activity, Bundle savedInstanceState) {
        super(activity, savedInstanceState);
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
        setEmptyTeamFlag(false);
    }
}
