package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * Created by chokwanghwan on 2017. 12. 13..
 */

public class NotificationFragmentViewAdapter {
    @BindingAdapter("notificationFragmentViewAdapter:initRecyclerView")
    public static void initRecyclerView(RecyclerView view, Boolean nothing) {
        view.setHasFixedSize(true);
        view.setItemAnimator(null);
    }
}
