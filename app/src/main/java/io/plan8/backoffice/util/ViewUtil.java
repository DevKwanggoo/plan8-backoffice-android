package io.plan8.backoffice.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Objects;

import io.plan8.backoffice.Constants;
import io.plan8.backoffice.model.api.Action;
import io.plan8.backoffice.model.api.Reservation;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ViewUtil {
    private static volatile ViewUtil instance = null;

    public static ViewUtil getInstance() {
        if (null == instance) {
            synchronized (ViewUtil.class) {
                instance = new ViewUtil();
            }
        }

        return instance;
    }

    public int dpToPx(double dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public int pxToDp(double px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public void hideKeyboard(View view) {
        InputMethodManager immhide = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        immhide.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void hideKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    public String getStatusText(Reservation reservation) {
        if (null == reservation || null == reservation.getStatus()) {
            return "대기";
        }
        if (reservation.getStatus().equals(Constants.RESERVATION_STATUS_COMPLETE)) {
            return "완료";
        } else if (reservation.getStatus().equals(Constants.RESERVATION_STATUS_INCOMPLETE)) {
            return "대기";
        } else {
            return "취소됨";
        }
    }

    public String getActionItemText(Action action) {
        String creatorName = "";
        if (null == action) {
            return "알림 내용 없음";
        }

        if (action.getType() != null && action.getCreator() != null) {
            if (null != action.getCreator().getName()) {
                creatorName = action.getCreator().getName();
            } else {
                creatorName = "(알 수 없음)";
            }
            String customerName = "고객명 없음";
            if (action.getReservation() != null && action.getReservation().getUser() != null && action.getReservation().getUser().getName() != null) {
                customerName = action.getReservation().getUser().getName();
            }

            if (action.getType().equals("comment")) {
                return creatorName + "님이 댓글을 달았습니다." + action.getText();
            } else if (action.getType().equals("statusChanged")) {
                String status = "알 수 없음";

                if (action.getData().getAfter().equals(Constants.RESERVATION_STATUS_COMPLETE)) {
                    status = "완료 처리 하였";
                } else if (action.getData().getAfter().equals(Constants.RESERVATION_STATUS_CANCELED)) {
                    status = "취소 하였";
                } else if (action.getData().getAfter().equals(Constants.RESERVATION_STATUS_INCOMPLETE)) {
                    status = "대기 상태로 변경하였";
                }

                return creatorName + "님이 "
                        + customerName + " 고객님의 " + DateUtil.getInstance().getReservationDate(action.getReservation().getStart()) + " 예약을 "
                        + status + "습니다.";
            } else if (action.getType().equals("phoneNumberChanged")) {
                return creatorName + "님이 " + customerName + " 고객님의 전화번호를 변경하였습니다.";
            } else if (action.getType().equals("emailChanged")) {
                return creatorName + "님이 " + customerName + " 고객님의 이메일을 변경하였습니다.";
            } else if (action.getType().equals("totalPriceChanged")) {
                return creatorName + "님이" + customerName + " 고객님의 " + DateUtil.getInstance().getReservationDate(action.getReservation().getStart()) + " 예약의 상품 가격을 변경하였습니다.";
            } else if (action.getType().equals("additionalRequestsChanged")) {
                return creatorName + "님이" + customerName + " 고객님의 " + DateUtil.getInstance().getReservationDate(action.getReservation().getStart()) + " 예약의 추가 요청 사항을 수정하였습니다.";
            } else {
                return "알림 내용 없음";
            }
        } else {
            return "알림 내용 없음";
        }
    }

    public String getDefaultAvatar(int userId) {
        return "http://assets.starshell.co/plan8/default/avatars/" + userId % 27 + ".png";
    }

    public String getDefaultTeamLogo(int teamId) {
        return "http://assets.starshell.co/plan8/default/avatars/" + teamId % 27 + ".png";
    }

    public String getCommaFormat(int num) {
        return String.format("%,d", num);
    }

    public String getActivityItemText(Action action){
        String creator = "(알 수 없음)";
        String afterData = "(알 수 없음)";

        if (action != null){
            if (action.getCreator() != null && action.getCreator().getName() != null && !action.getCreator().getName().equals("")) {
                creator = action.getCreator().getName();
            }

            if (action.getData() != null && action.getData().getAfter() != null){
                afterData = action.getData().getAfter();
            }

            if (action.getType() != null){
                if (action.getType().equals("statusChanged")){
                    String status = "";
                    if (action.getData().getAfter().equals(Constants.RESERVATION_STATUS_COMPLETE)){
                        status = "완료 처리 하였";
                    } else if (action.getData().getAfter().equals(Constants.RESERVATION_STATUS_INCOMPLETE)){
                        status = "대기 상태로 변경하였";
                    } else {
                        status = "취소 하였";
                    }
                    return creator + "님이 예약을" + status + "습니다.";
                } else if (action.getType().equals("phoneNumberChanged")){
                    return creator + "님이 고객님의 전화번호를 "+ afterData + "로 변경하였습니다.";
                } else if (action.getType().equals("emailChanged")){
                    return creator + "님이 고객님의 이메일을 "+ afterData + "로 변경하였습니다.";
                } else if (action.getType().equals("totalPriceChanged")){
                    return creator + "님이 상품 가격을 "+ afterData + "원 으로 변경하였습니다.";
                } else if (action.getType().equals("additionalRequestsChanged")){
                    return creator + "님이 추가 요청 사항을 수정하였습니다.";
                }
            }
        }

        return "내용 없음";
    }
}
