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

import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.view.BlurView;
import io.plan8.backoffice.view.Plan8TaskCalendarView;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class TaskViewAdapter {
    @BindingAdapter("taskViewAdapter:displayCalendar")
    public static void setDisplayCalendar(final Plan8TaskCalendarView view, Boolean isOpenedCalendar) {
        Animation slideDownAnimation;
        Animation slideUpAnimation;

        if (!view.isAlreadyInflated()) {
            slideDownAnimation = null;
            slideUpAnimation = null;
            view.setSelectedDate(CalendarDay.today());
            view.setAlreadyInflated(true);
            view.setVisibility(View.INVISIBLE);
            view.setPagingEnabled(false);
            return;
        }

        slideDownAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_down);
        slideDownAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
                view.setPagingEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        slideUpAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_up);
        slideUpAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
                view.setPagingEnabled(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

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
                return "" + day.getYear() + "년 " + (day.getMonth() + 1) + "월";
            }
        });
    }

    @BindingAdapter("taskViewAdapter:setReadStatus")
    public static void setReadStatus(RelativeLayout view, String taskStatus) {
        view.setBackgroundResource(R.drawable.circle);
        GradientDrawable bgShape = (GradientDrawable) view.getBackground();

        if (taskStatus.equals(Constants.TASK_STATUS_BLUE)) {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusBlue));
        } else if (taskStatus.equals(Constants.TASK_STATUS_ORANGE)) {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusOrange));
        } else if (taskStatus.equals(Constants.TASK_STATUS_RED)) {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusRed));
        } else {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.transparent));
        }
    }

    @BindingAdapter("taskViewAdapter:setCloseStatus")
    public static void setCloseStatus(RelativeLayout view, String taskStatus) {
        view.setBackgroundResource(R.drawable.circle);
        GradientDrawable bgShape = (GradientDrawable) view.getBackground();
        if (taskStatus == Constants.TASK_STATUS_GREEN) {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusGreen));
        } else {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.transparent));
        }
    }

    @BindingAdapter("taskViewAdapter:fadeout")
    public static void fadeout(final BlurView view, Boolean display) {
        if (!view.isAlreadyInflated()) {
            view.setAlreadyInflated(true);
            view.setVisibility(View.GONE);
            return;
        }
        if (display) {
            view.setVisibility(View.VISIBLE);
        } else {
            AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
            animation.setDuration(200);
            animation.setInterpolator(new AccelerateDecelerateInterpolator());
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            view.setVisibility(View.VISIBLE);
            view.startAnimation(animation);
        }
    }
}
