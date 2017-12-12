package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.activity.DetailReservationActivity;
import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 12. 1..
 */

public class MentionItemVM extends ActivityVM {
    private Member member;

    public MentionItemVM(Activity activity, Bundle savedInstanceState, Member member) {
        super(activity, savedInstanceState);
        this.member = member;
    }

    @Bindable
    public String getName() {
        if (member != null && member.getName() != null){
            return member.getName();
        }
        return "이름없음";
    }

    @Bindable
    public String getUserName(){
        if (member != null && member.getUsername() != null){
            return "@" + member.getUsername();
        }
        return "비어있음";
    }

    @Bindable
    public String getAvatar() {
        if (null == member) {
            return "";
        }
        return member.getAvatar();
    }

    public void clickMention(View view) {
        if (getActivity() instanceof DetailReservationActivity) {
            ((DetailReservationActivity) getActivity()).replaceToMention(member);
        }
    }
}
