package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;

import io.plan8.backoffice.model.api.Action;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.util.ViewUtil;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by SSozi on 2017. 11. 29..
 */

public class DetailReservationActionReplaceItemVM extends ActivityVM {
    private Action action;

    public DetailReservationActionReplaceItemVM(Activity activity, Bundle savedInstanceState, final Action action) {
        super(activity, savedInstanceState);
        this.action = action;
    }

    @Bindable
    public String getReplaceTitle() {
        return ViewUtil.getInstance().getActionItemText(action);
    }

    @Bindable
    public String getCreatedDate() {
        if (null == action) {
            return "";
        }
        return DateUtil.getInstance().getChatTime(action.getAdded());
    }
}