package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class MainViewAdapter {
    @BindingAdapter("mainViewAdapter:isEmptyTeam")
    public static void isEmptyTeam(RelativeLayout view, Boolean isEmptyTeamFlag) {
        if (isEmptyTeamFlag){
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
