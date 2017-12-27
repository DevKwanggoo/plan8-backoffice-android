package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import java.text.DecimalFormat;

import io.plan8.backoffice.Constants;
import io.plan8.backoffice.activity.BaseActivity;
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

    public DetailReservationHeaderItemVM(BaseActivity activity, Bundle savedInstanceState, Reservation reservation) {
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
            return "고객 전화번호 없음";
        }
        return reservation.getPhoneNumber();
    }

    @Bindable
    public String getCustomerAddress() {
        if (null == reservation || null == reservation.getAddress()) {
            return "고객 주소 없음";
        }
        return reservation.getAddress().getName();
    }

    @Bindable
    public String getReservationDate() {
        if (null == reservation) {
            return "날짜 없음";
        }
        return DateUtil.getInstance().getReservationDate(reservation.getStart());
    }

    @Bindable
    public String getReservationTime() {
        if (null == reservation) {
            return "예약시간 없음";
        }
        return DateUtil.getInstance().getReservationTime(reservation.getStart());
    }

    @Bindable
    public String getReservationEndTime() {
        if (null == reservation) {
            return "종료시간 없음";
        }
        return DateUtil.getInstance().getReservationTime(reservation.getEnd());
    }

    @Bindable
    public String getProductName() {
        if (null == reservation
                || null == reservation.getProduct()) {
            return "상품이름 없음";
        }

        return "" + reservation.getProduct().getName();
    }

    @Bindable
    public String getCustomerRequest() {
        if (reservation != null && reservation.getAddtionalRequest() != null) {
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
            if (!reservation.getStatus().equals(Constants.RESERVATION_STATUS_CANCELED)
                    && getActivity() instanceof DetailReservationActivity) {
                ((DetailReservationActivity) getActivity()).showBottomSheet();
            }
        }
    }

    @Bindable
    public String getCustomerTeamName() {
        if (null == reservation || null == reservation.getTeam()) {
            return "팀 이름 없음";
        }
        return reservation.getTeam().getName();
    }

    @Bindable
    public boolean getStateFlag() {
        if (reservation != null && reservation.getStatus() != null) {
            if (reservation.getStatus().equals(Constants.RESERVATION_STATUS_CANCELED)) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    @Bindable
    public String getProductPrice() {
        if (reservation != null
                && reservation.getProduct() != null) {
            DecimalFormat formatter = new DecimalFormat("#,###");
            return formatter.format(reservation.getProduct().getPrice()) + "원";
        }
        return "가격 정보 없음";
    }
}
