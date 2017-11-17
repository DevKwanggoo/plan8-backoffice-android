package io.plan8.backoffice.adapter

import android.databinding.BindingAdapter
import android.view.View
import android.widget.RelativeLayout

/**
 * Created by chokwanghwan on 2017. 11. 9..
 */
class MainViewAdapter {
    companion object {
        @BindingAdapter("mainViewAdapter:isEmptyTeam")
        @JvmStatic
        fun isEmptyTeam(view: RelativeLayout, isEmptyTeamFlag: Boolean) {
            when (isEmptyTeamFlag){
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.GONE
            }
        }
    }
}