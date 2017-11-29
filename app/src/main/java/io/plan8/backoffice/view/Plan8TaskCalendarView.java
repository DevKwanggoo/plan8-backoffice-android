package io.plan8.backoffice.view;

import android.content.Context;
import android.util.AttributeSet;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Plan8TaskCalendarView extends MaterialCalendarView {
    private boolean isAlreadyInflated = false;

    public Plan8TaskCalendarView(Context context) {
        super(context);
    }

    public Plan8TaskCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean isAlreadyInflated() {
        return isAlreadyInflated;
    }

    public void setAlreadyInflated(boolean alreadyInflated) {
        isAlreadyInflated = alreadyInflated;
    }
}
