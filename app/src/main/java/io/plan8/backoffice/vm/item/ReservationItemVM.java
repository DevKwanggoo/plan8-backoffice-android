package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.DetailReservationActivity;
import io.plan8.backoffice.fragment.BaseFragment;
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.FragmentVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ReservationItemVM extends FragmentVM {
    private Reservation reservation;

    public ReservationItemVM(BaseFragment fragment, Bundle savedInstanceState, Reservation reservation) {
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
    public String getCustomerAndTeamName() {
        String customerName = "";
        String teamName = "";

        if (null != reservation) {
            if (null != reservation.getUser()) {
                customerName = reservation.getUser().getName();
            } else {
                customerName = "고객명 없음";
            }

            if (null != reservation.getTeam()
                    && null != reservation.getTeam()) {
                teamName = reservation.getTeam().getName();
            }
        }
        if (teamName.equals("")) {
            return customerName;
        } else {
            return customerName + "ㆍ" + teamName;
        }
    }

    @Bindable
    public String getCustomerAddress() {
        if (null == reservation || null == reservation.getAddress()) {
            return "주소 없음";
        }

        return reservation.getAddress().getName();
    }

    @Bindable
    public String getProductName() {
        if (null == reservation
                || null == reservation.getProduct()) {
            return "";
        }
        return "" + reservation.getProduct().getName();
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
