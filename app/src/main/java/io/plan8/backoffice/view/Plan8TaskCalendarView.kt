package io.plan8.backoffice.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

/**
 * Created by chokwanghwan on 2017. 11. 6..
 */
class Plan8TaskCalendarView : MaterialCalendarView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    var isAlreadyInflated: Boolean = false
}