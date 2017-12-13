package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.DetailReservationActivity;
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.FragmentVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ReservationItemVM extends FragmentVM {
    private Reservation reservation;

    public ReservationItemVM(Fragment fragment, Bundle savedInstanceState, Reservation reservation) {
        super(fragment, savedInstanceState);
        this.reservation = reservation;
    }

    @Bindable
    public String getReservationTime() {
        if (null == reservation) {
            return "";
        }
        return DateUtil.getInstance().getReservationTime(reservation.getStart());
    }

    @Bindable
    public String getCustomerName() {
        if (null == reservation
                || null == reservation.getUser() || null == reservation.getUser().getName()
                || reservation.getUser().getName().equals("")) {

            return "고객명 없음";
        }
        return reservation.getUser().getName();
    }

    @Bindable
    public String getCustomerAddress() {
        if (null == reservation || null == reservation.getAddress()) {
            return "";
        }

        return reservation.getAddress().getName();
    }

    @Bindable
    public String getProductName() {
        if (null == reservation
                || null == reservation.getProducts()
                || null == reservation.getProducts().get(0)) {
            return "";
        }
        return "" + reservation.getProducts().get(0).getName();
    }

    @Bindable
    public String getReservationStatus() {
        if (null == reservation) {
            return "";
        }
        return reservation.getStatus();
    }

    @Bindable
    public String getReservationCloseStatus() {
        if (null == reservation) {
            return "";
        }
        return reservation.getStatus();
    }

    public void showDetailReservation(View view) {
        getFragment().startActivityForResult(DetailReservationActivity.buildIntent(getFragment().getContext(), reservation.getId()), Constants.REFRESH_RESERVATION_FRAGMENT);
        getFragment().getActivity().overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
    }
}
