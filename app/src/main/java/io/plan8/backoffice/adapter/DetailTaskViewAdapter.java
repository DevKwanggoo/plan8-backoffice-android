package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;

import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class DetailTaskViewAdapter {
    @BindingAdapter("detailTaskViewAdapter:isTaskStatus")
    public static void isTaskStatus(RoundedImageView view, String taskStatus) {
        if (taskStatus == "완료"){
            view.setBackgroundColor(ContextCompat.getColor(view.getContext() ,R.color.taskStatusBlue))
        } else {
            view.setBackgroundColor(ContextCompat.getColor(view.getContext() ,R.color.taskStatusRed))
        }
    }
}
