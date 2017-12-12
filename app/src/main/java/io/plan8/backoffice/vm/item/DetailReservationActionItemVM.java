package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;

import io.plan8.backoffice.model.api.Action;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class DetailReservationActionItemVM extends ActivityVM {
    private Action action;

    public DetailReservationActionItemVM(Activity activity, Bundle savedInstanceState, Action action) {
        super(activity, savedInstanceState);
        this.action = action;
    }

    @Bindable
    public String getImageUrl() {
        if (null == action || null == action.getCreator()) {
            return "";
        }
        return action.getCreator().getAvatar();
    }

    @Bindable
    public String getAuthor() {
        if (null == action || null == action.getCreator()) {
            return "";
        }
        return action.getCreator().getName();
    }

    @Bindable
    public String getAction() {
        if (null == action) {
            return "";
        }
        return action.getText();
    }


    @Bindable
    public String getCreated() {
        if (null == action) {
            return "";
        }
        return DateUtil.getInstance().getChatTime(action.getCreated());
    }
}