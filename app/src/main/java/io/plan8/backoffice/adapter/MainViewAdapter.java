package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.RelativeLayout;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class MainViewAdapter {
    @BindingAdapter("mainViewAdapter:isEmptyTeam")
    public static void isEmptyTeam(RelativeLayout view, Boolean isEmptyTeamFlag) {
        if (isEmptyTeamFlag) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("android:setNotificationCount")
    public static void setNotificationCount(BGABadgeImageView view, int notificationCount) {
//        int prevCartCount = SharedPreferenceManager.getInstance().getCartCount();
        view.showTextBadge("" + notificationCount);
        if (notificationCount == 0) {
            view.hiddenBadge();
        }

//        if (cartInfo.getCartCount() > prevCartCount) {
//            view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.shake_cart));
//        }
//        new SharedPreferenceManager(view.getContext()).setCartCount(cartInfo.getCartCount());
    }
}
