package io.plan8.business.adapter

import android.databinding.BindingAdapter
import android.view.View
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.TitleFormatter


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
    }
}