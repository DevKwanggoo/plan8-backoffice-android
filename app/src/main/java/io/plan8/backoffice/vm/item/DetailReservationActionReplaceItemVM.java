package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;

import io.plan8.backoffice.activity.BaseActivity;
import io.plan8.backoffice.model.api.Action;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by SSozi on 2017. 11. 29..
 */

public class DetailReservationActionReplaceItemVM extends ActivityVM {
    private Action action;

    public DetailReservationActionReplaceItemVM(BaseActivity activity, Bundle savedInstanceState, final Action action) {
        super(activity, savedInstanceState);
        this.action = action;
    }

    @Bindable
    public Action getAction() {
        return action;
    }
}