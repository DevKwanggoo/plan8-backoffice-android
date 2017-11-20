package io.plan8.backoffice.adapter

import android.databinding.BindingAdapter
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.Toast
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import io.plan8.backoffice.Constants
import io.plan8.backoffice.R
import io.plan8.backoffice.view.BlurView
import io.plan8.backoffice.view.Plan8TaskCalendarView
import kotlinx.android.synthetic.main.activity_main.view.*


/**
 * Created by chokwanghwan on 2017. 11. 3..
 */
class TaskViewAdapter {
    companion object {
        @BindingAdapter("taskViewAdapter:displayCalendar")
        @JvmStatic
        fun setDisplayCalendar(view: Plan8TaskCalendarView, isOpenedCalendar: Boolean) {
            if (!view.isAlreadyInflated) {
                view.selectedDate = CalendarDay.today()
                view.isAlreadyInflated = true
                view.visibility = View.INVISIBLE
                view.isPagingEnabled = false
                return
            }
            var slideDownAnimation: Animation? = null
            var slideUpAnimation: Animation? = null

            if (slideDownAnimation == null || slideUpAnimation == null) {
                slideDownAnimation = AnimationUtils.loadAnimation(view.context, R.anim.slide_down)
                slideDownAnimation!!.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationRepeat(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {
                        view.visibility = View.VISIBLE
                        view.isPagingEnabled = true
                    }
                })

                slideUpAnimation = AnimationUtils.loadAnimation(view.context, R.anim.slide_up)
                slideUpAnimation!!.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationRepeat(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {
                        view.visibility = View.INVISIBLE
                        view.isPagingEnabled = false
                    }
                })
            }

            if (isOpenedCalendar) {
                view.startAnimation(slideDownAnimation)
            } else {
                view.startAnimation(slideUpAnimation)
            }
        }

        @BindingAdapter("taskViewAdapter:initCalendar")
        @JvmStatic
        fun initCalendar(view: MaterialCalendarView, isOpenedCalendar: Boolean) {
            view.setTitleFormatter({ day: CalendarDay? -> "" + day!!.year + "년 " + (day.month + 1) + "월" })
        }

        @BindingAdapter("taskViewAdapter:setStatus")
        @JvmStatic
        fun setStatus(view: RelativeLayout, taskStatus: String) {
            view.setBackgroundResource(R.drawable.circle)
            val bgShape = view.background as GradientDrawable
            when (taskStatus) {
                Constants.TASK_STATUS_BLUE -> bgShape.setColor(ContextCompat.getColor(view.context, R.color.taskStatusBlue))
                Constants.TASK_STATUS_ORANGE -> bgShape.setColor(ContextCompat.getColor(view.context, R.color.taskStatusOrange))
                else -> bgShape.setColor(ContextCompat.getColor(view.context, R.color.taskStatusRed))
            }
        }

        @BindingAdapter("taskViewAdapter:fadeout")
        @JvmStatic
        fun fadeout(view: BlurView, display: Boolean) {
            if (!view.isAlreadyInflated) {
                view.isAlreadyInflated = true
                view.visibility = View.GONE
                return
            }
            if (display) {
                view.visibility = View.VISIBLE
            } else {
                val animation = AlphaAnimation(1.0f, 0.0f)
                animation.duration = 200
                animation.interpolator = AccelerateDecelerateInterpolator()
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {

                    }

                    override fun onAnimationEnd(animation: Animation) {
                        view.visibility = View.GONE
                    }

                    override fun onAnimationRepeat(animation: Animation) {

                    }
                })
                view.visibility = View.VISIBLE
                view.startAnimation(animation)
            }
        }
    }
}