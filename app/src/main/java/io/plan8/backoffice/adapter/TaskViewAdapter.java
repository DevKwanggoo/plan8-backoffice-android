package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class TaskViewAdapter {
    @BindingAdapter("taskViewAdapter:displayCalendar")
    public static void setDisplayCalendar(Plan8TaskCalendarView view, Boolean isOpenedCalendar) {
        Animation slideDownAnimation;
        Animation slideUpAnimation;

        if (!view.isAlreadyInflated) {
            slideDownAnimation = null;
            slideUpAnimation = null;
            view.selectedDate = CalendarDay.today();
            view.isAlreadyInflated = true;
            view.visibility = View.INVISIBLE;
            view.isPagingEnabled = false;
            return;
        }

        if (slideDownAnimation == null || slideUpAnimation == null) {
            slideDownAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_down)
            slideDownAnimation.setAnimationListener(new Animation.AnimationListener {
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
            view.startAnimation(slideDownAnimation);
        } else {
            view.startAnimation(slideUpAnimation);
        }
    }

    @BindingAdapter("taskViewAdapter:initCalendar")
    public static void initCalendar(MaterialCalendarView view, Boolean isOpenedCalendar) {
        view.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                return "" + day.getYear() + "년 "+(day.getMonth()+1)+"월";
            }
        });
    }

    @BindingAdapter("taskViewAdapter:setReadStatus")
    public static void setReadStatus(RelativeLayout view, String taskStatus) {
        view.setBackgroundResource(R.drawable.circle);
        GradientDrawable bgShape = (GradientDrawable) view.getBackground();

        if (taskStatus == Constants.TASK_STATUS_BLUE){
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusBlue));
        } else if (taskStatus == Constants.TASK_STATUS_ORANGE) {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusOrange));
        } else if (taskStatus == Constants.TASK_STATUS_RED) {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusRed));
        } else {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.transparent));
        }
    }

    @BindingAdapter("taskViewAdapter:setCloseStatus")
    public static void setCloseStatus(RelativeLayout view, String taskStatus) {
        view.setBackgroundResource(R.drawable.circle);
        GradientDrawable bgShape = (GradientDrawable) view.getBackground();
        if (taskStatus == Constants.TASK_STATUS_GREEN){
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusGreen));
        } else {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.transparent));
        }
    }

    @BindingAdapter("taskViewAdapter:fadeout")
    public static void fadeout(BlurView view, Boolean display) {
        if (!view.isAlreadyInflated) {
            view.isAlreadyInflated = true;
            view.visibility = View.GONE;
            return
        }
        if (display) {
            view.visibility = View.VISIBLE;
        } else {
            AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
            animation.setDuration(200);
            animation.setInterpolator(new AccelerateDecelerateInterpolator());
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {

                }

                override fun onAnimationEnd(animation: Animation) {
                    view.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animation) {

                }
            })
            view.visibility = View.VISIBLE;
            view.startAnimation(animation);
        }
    }
}
