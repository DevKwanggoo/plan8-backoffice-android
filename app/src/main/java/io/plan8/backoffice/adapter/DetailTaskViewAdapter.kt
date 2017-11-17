package io.plan8.backoffice.adapter

import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.RelativeLayout
import com.makeramen.roundedimageview.RoundedImageView
import io.plan8.backoffice.R

/**
 * Created by SSozi on 2017. 11. 16..
 */
class DetailTaskViewAdapter {
    companion object {
        @BindingAdapter("detailTaskViewAdapter:isTaskStatus")
        @JvmStatic
        fun isTaskStatus(view: RoundedImageView, taskStatus: String) {
            if (taskStatus == "완료"){
                view.setBackgroundColor(ContextCompat.getColor(view.context ,R.color.taskStatusBlue))
            } else {
                view.setBackgroundColor(ContextCompat.getColor(view.context ,R.color.taskStatusRed))
            }
        }
    }
}