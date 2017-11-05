package io.plan8.business.adapter

import android.databinding.BindingAdapter
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.RelativeLayout
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import io.plan8.business.Constants
import io.plan8.business.R


/**
 * Created by chokwanghwan on 2017. 11. 3..
 */
class TaskViewAdapter {
    companion object {
        @BindingAdapter("taskViewAdapter:displayCalendar")
        @JvmStatic
        fun setDisplayCalendar(view: MaterialCalendarView, isOpenedCalendar: Boolean) {

            if (isOpenedCalendar) {
                view.visibility = View.VISIBLE
//                view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.slide_down))
            } else {
                view.visibility = View.GONE
//                view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.slide_up))
//                view.layoutAnimationListener
            }

//            mAni1 = AnimationUtils.loadAnimation(this, R.anim.rotate);
//            mAni1.setAnimationListener(new AnimationListener(){
//                public void onAnimationEnd(Animation animation){
//                    // To Do .. View.startAnimation(mAni2);
//                }
//
//                public void onAnimationStart(Animation animation){;}

        }

        @BindingAdapter("taskViewAdapter:initCalendar")
        @JvmStatic
        fun initCalendar(view: MaterialCalendarView, isOpenedCalendar: Boolean) {
            view.setTitleFormatter(TitleFormatter { day: CalendarDay? -> "" + day!!.year + "년 " + (day.month + 1) + "월" })
        }

        @BindingAdapter("taskViewAdapter:setStatus")
        @JvmStatic
        fun setStatus(view: RelativeLayout, taskStatus: String) {
            view.setBackgroundResource(R.drawable.circle)
            val bgShape = view.getBackground() as GradientDrawable
            if (taskStatus.equals(Constants.TASK_STATUS_BLUE)) {
                bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusBlue))
            } else if (taskStatus.equals(Constants.TASK_STATUS_ORANGE)) {
                bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusOrange))
            } else {
                bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusRed))
            }
        }
    }
}