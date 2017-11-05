package io.plan8.business.vm

import android.databinding.Bindable
import android.os.Bundle
import android.view.View
import io.plan8.business.BR
import io.plan8.business.activity.TaskActivity
import io.plan8.business.util.DateUtil

/**
 * Created by SSozi on 2017. 11. 2..
 */
class TaskActivityVM(activity: TaskActivity, savedInstanceState: Bundle?) : ActivityVM(activity, savedInstanceState) {
    var selectedDate: String = ""
        get() {
            return field
        }
        set(selectedDate) {
            field = selectedDate
            toolbarTitle = selectedDate
        }

    var isOpenedCalendar: Boolean = false
        @Bindable
        get() {
            return field
        }
        set (isOpenedCalendar) {
            field = isOpenedCalendar
            notifyPropertyChanged(BR.openedCalendar)
            notifyPropertyChanged(BR.toolbarTitle)
        }

    var toolbarTitle: String = ""
        @Bindable
        get() {
            if (isOpenedCalendar) {
                return "날짜변경"
            } else {
                return selectedDate
            }
        }
        set (toolbarTitle) {
            field = toolbarTitle
            notifyPropertyChanged(BR.toolbarTitle)
        }

    fun changeDate(view: View) {
        isOpenedCalendar = !isOpenedCalendar
    }

    init {
        selectedDate = DateUtil.getCurrentDate()
    }
}