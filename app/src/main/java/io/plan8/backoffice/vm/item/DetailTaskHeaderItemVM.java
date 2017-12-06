package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.Constants;
import io.plan8.backoffice.activity.DetailTaskActivity;
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class DetailTaskHeaderItemVM extends ActivityVM {
    private Reservation reservation;

    public DetailTaskHeaderItemVM(Activity activity, Bundle savedInstanceState, Reservation reservation) {
        super(activity, savedInstanceState);
        this.reservation = reservation;
    }

    @Bindable
    public String getCustomerName() {
        if (null == reservation || null == reservation.getUser()) {
            return "";
        }
        return reservation.getUser().getUserName();
    }

    @Bindable
    public String getCustomerPhoneNumber() {
        if (null == reservation) {
            return "";
        }
        return reservation.getMobileNumber();
    }

    @Bindable
    public String getCustomerAddress() {
        if (null == reservation || null == reservation.getAddress()) {
            return "";
        }
        return reservation.getAddress().getName();
    }

    @Bindable
    public String getReservationDate() {
        if (null == reservation) {
            return "";
        }
        return reservation.getStart();
    }

    @Bindable
    public String getReservationTime() {
        if (null == reservation) {
            return "";
        }
        return reservation.getStart();
    }

    @Bindable
    public String getReservationEndTime() {
        if (null == reservation) {
            return "";
        }
        return reservation.getEnd();
    }

    @Bindable
    public String getProductionName() {
        if (null == reservation) {
            return "";
        }
        return "" + reservation.getTotalPrice();
    }

    @Bindable
    public String getCustomerRequest() {
        if (null == reservation) {
            return "";
        }
        return reservation.getAddtionalRequest();
    }

    @Bindable
    public String getProductionDescription() {
        if (null == reservation) {
            return "";
        }
        return "";
    }

    @Bindable
    public String getReservationStatus() {
        if (null != reservation && reservation.equals(Constants.TASK_STATUS_BLUE)) {
            return "완료";
        } else {
            return "미완료";
        }
    }

    public void editTaskStatus(View view) {
        if (getActivity() instanceof DetailTaskActivity) {
            ((DetailTaskActivity) getActivity()).showBottomSheet();
        }
    }
}
