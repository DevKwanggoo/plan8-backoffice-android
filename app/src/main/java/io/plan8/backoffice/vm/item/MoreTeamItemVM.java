package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.model.item.MoreTeamItem;
import io.plan8.backoffice.vm.FragmentVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class MoreTeamItemVM extends FragmentVM {
    private MoreTeamItem moreTeamItem;
    private boolean clickFlag;
    public MoreTeamItemVM(Fragment fragment, Bundle savedInstanceState, MoreTeamItem moreTeamItem) {
        super(fragment, savedInstanceState);
        this.moreTeamItem = moreTeamItem;
    }

    @Bindable
    public String getTeamName() {
        if (null == moreTeamItem) {
            return "";
        }
        return moreTeamItem.getName();
    }

    @Bindable
    public String getTeamDescription() {
        if (null == moreTeamItem) {
            return "";
        }
        return moreTeamItem.getDescription();
    }

    @Bindable
    public boolean getSelectTeamFlag() {
        return clickFlag;
    }

    public void setSelectTeamFlag(boolean flag) {
        clickFlag = flag;
        notifyPropertyChanged(BR.selectTeamFlag);
    }

    public void selectTeam(View view) {
        setSelectTeamFlag(!clickFlag);
    }

    @Bindable
    public String getTeamLogo(){
        if (null != moreTeamItem.getLogo()){
            return moreTeamItem.getLogo();
        }
        return "";
    }
}