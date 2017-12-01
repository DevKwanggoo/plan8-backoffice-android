package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.activity.DetailTaskActivity;
import io.plan8.backoffice.model.item.DetailTaskMoreButtonItem;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class DetailTaskMoreButtonItemVM extends ActivityVM{
    private DetailTaskMoreButtonItem detailTaskMoreButtonItem;
    public DetailTaskMoreButtonItemVM(Activity activity, Bundle savedInstanceState, DetailTaskMoreButtonItem detailTaskMoreButtonItem) {
        super(activity, savedInstanceState);
        this.detailTaskMoreButtonItem = detailTaskMoreButtonItem;
    }

    @Bindable
    public String getText() {
        if (null == detailTaskMoreButtonItem) {
            return "";
        }
        return detailTaskMoreButtonItem.getText();
    }

    public void callMoreComment(View view) {
        if (getActivity() instanceof DetailTaskActivity) {
            ((DetailTaskActivity) getActivity()).callMoreComment();
        }
    }
}
