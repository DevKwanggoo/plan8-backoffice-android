package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.activity.BaseActivity;
import io.plan8.backoffice.activity.DetailReservationActivity;
import io.plan8.backoffice.model.item.DetailReservationMoreButtonItem;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class DetailReservationMoreButtonItemVM extends ActivityVM {
    private DetailReservationMoreButtonItem detailReservationMoreButtonItem;

    public DetailReservationMoreButtonItemVM(BaseActivity activity, Bundle savedInstanceState, DetailReservationMoreButtonItem detailReservationMoreButtonItem) {
        super(activity, savedInstanceState);
        this.detailReservationMoreButtonItem = detailReservationMoreButtonItem;
    }

    @Bindable
    public String getText() {
        if (null == detailReservationMoreButtonItem) {
            return "";
        }
        return detailReservationMoreButtonItem.getText();
    }

    public void callMoreComment(View view) {
        if (getActivity() instanceof DetailReservationActivity) {
            ((DetailReservationActivity) getActivity()).refreshActionData();
        }
    }
}