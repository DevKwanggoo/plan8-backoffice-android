package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.activity.BaseActivity;
import io.plan8.backoffice.activity.DetailReservationActivity;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 12. 1..
 */

public class MentionItemVM extends ActivityVM {
    private User user;

    public MentionItemVM(BaseActivity activity, Bundle savedInstanceState, User user) {
        super(activity, savedInstanceState);
        this.user = user;
    }

    @Bindable
    public String getName() {
        if (user != null && user.getName() != null){
            return user.getName();
        }
        return "이름없음";
    }

    @Bindable
    public String getUserName(){
        if (user != null && user.getUsername() != null){
            return "@" + user.getUsername();
        }
        return "비어있음";
    }

    @Bindable
    public String getAvatar() {
        if (null == user) {
            return "";
        }
        return user.getAvatar();
    }

    public void clickMention(View view) {
        if (getActivity() instanceof DetailReservationActivity) {
            ((DetailReservationActivity) getActivity()).replaceToMention(user);
        }
    }
}
