package io.plan8.business.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * Created by chokwanghwan on 2017. 11. 7..
 */
class BlurView : LinearLayout {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    var isAlreadyInflated: Boolean = false
}