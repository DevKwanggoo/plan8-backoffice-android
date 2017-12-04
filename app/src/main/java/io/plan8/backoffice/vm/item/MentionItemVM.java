package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.activity.DetailTaskActivity;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 12. 1..
 */

public class MentionItemVM extends ActivityVM {
    private User user;

    public MentionItemVM(Activity activity, Bundle savedInstanceState, User user) {
        super(activity, savedInstanceState);
        this.user = user;
    }

    @Bindable
    public String getName() {
        if (null == user) {
            return "";
        }
        return user.getUserName();
    }

    @Bindable
    public String getAvatar() {
        if (null == user) {
            return "";
        }
        return user.getAvatar();
    }

    public void clickMention(View view) {
        if (getActivity() instanceof DetailTaskActivity) {
            ((DetailTaskActivity) getActivity()).replaceToMention(user);
        }
    }
}
