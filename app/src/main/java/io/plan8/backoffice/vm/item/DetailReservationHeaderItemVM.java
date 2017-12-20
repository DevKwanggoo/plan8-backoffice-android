package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.Constants;
import io.plan8.backoffice.activity.DetailReservationActivity;
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.util.ViewUtil;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class DetailReservationHeaderItemVM extends ActivityVM {
    private Reservation reservation;

    public DetailReservationHeaderItemVM(Activity activity, Bundle savedInstanceState, Reservation reservation) {
        super(activity, savedInstanceState);
        this.reservation = reservation;
    }

    @Bindable
    public String getCustomerName() {
        if (null == reservation || null == reservation.getUser() || null == reservation.getUser().getName()) {
            return "고객명 없음";
        }
        return reservation.getUser().getName();
    }

    @Bindable
    public String getCustomerPhoneNumber() {
        if (null == reservation) {
            return "";
        }
        return reservation.getPhoneNumber();
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
        return DateUtil.getInstance().getReservationDate(reservation.getStart());
    }

    @Bindable
    public String getReservationTime() {
        if (null == reservation) {
            return "";
        }
        return DateUtil.getInstance().getReservationTime(reservation.getStart());
    }

    @Bindable
    public String getReservationEndTime() {
        if (null == reservation) {
            return "";
        }
        return DateUtil.getInstance().getReservationTime(reservation.getEnd());
    }

    @Bindable
    public String getProductName() {
        if (null == reservation
                || null == reservation.getProducts()
                || null == reservation.getProducts().get(0)) {
            return "";
        }

        return "" + reservation.getProducts().get(0).getName() + " (" + ViewUtil.getInstance().getCommaFormat(reservation.getValue()) + "원)";
    }

    @Bindable
    public String getCustomerRequest() {
        if (reservation != null && reservation.getAddtionalRequest() != null){
            return reservation.getAddtionalRequest();
        }
        return "추가 요청 사항 없음";
    }

    @Bindable
    public String getReservationStatus() {
        if (null == reservation) {
            return Constants.RESERVATION_STATUS_INCOMPLETE;
        }
        return reservation.getStatus();
    }

    @Bindable
    public String getReservationStatusText() {
        return ViewUtil.getInstance().getStatusText(reservation);
    }

    public void editReservationStatus(View view) {
        if (reservation != null && reservation.getStatus() != null) {
            if (reservation.getStatus().equals(Constants.RESERVATION_STATUS_INCOMPLETE)) {
                if (getActivity() instanceof DetailReservationActivity) {
                    ((DetailReservationActivity) getActivity()).showBottomSheet();
                }
            }
        }
    }

    @Bindable
    public String getCustomerTeamName() {
        if (null == reservation) {
            return "";
        }
        return "픽스나우";
    }
}
