package io.plan8.backoffice.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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

    public void hideKeyboard(Context context){
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
            return "미완료";
        }
    }

    public String getActionItemText(Action action) {
        if (null == action) {
            return "";
        }

        if (action.getType().equals("commentAdded")) {
            return action.getCreator().getName() + "님이 작업 댓글을 추가하였습니다.";
        } else if (action.getType().equals("statusChanged")) {
            return action.getCreator().getName() + "님이 작업 상태를 수정하였습니다.";
        } else if (action.getType().equals("phoneNumberChanged")) {
            return action.getCreator().getName() + "님이 고객 연락처를 수정하였습니다.";
        } else if (action.getType().equals("emailChanged")) {
            return action.getCreator().getName() + "님이 이메일을 수정하였습니다.";
        } else if (action.getType().equals("totalPriceChanged")) {
            return action.getCreator().getName() + "님이 상품 가격을 수정하였습니다.";
        } else if (action.getType().equals("additionalRequestsChanged")) {
            return action.getCreator().getName() + "님이 추가요청 사항을 수정하였습니다.";
        } else if (action.getType().equals("action")) {
            return action.getCreator().getName() + "님이 action을 수정하였습니다.";
        } else {
            return "";
        }
    }

    public String getCommaFormat(int num) {
        return String.format("%,d", num);
    }
}
