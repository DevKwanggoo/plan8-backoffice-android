package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.vm.FragmentVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class MoreTeamItemVM extends FragmentVM {
    private Member member;

    public MoreTeamItemVM(Fragment fragment, Bundle savedInstanceState, Member member) {
        super(fragment, savedInstanceState);
        this.member = member;
    }

    @Bindable
    public String getTeamName() {
        if (null == member || null == member.getTeam()) {
            return "";
        }
        return member.getTeam().getName();
    }

    @Bindable
    public String getTeamDescription() {
        if (null == member || null == member.getTeam()) {
            return "";
        }
        return member.getTeam().getName();
    }

    @Bindable
    public String getTeamLogo() {
        if (null == member || null == member.getTeam()) {
            return "";
        }
        return member.getTeam().getLogo();
    }
}