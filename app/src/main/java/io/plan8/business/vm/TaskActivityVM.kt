package io.plan8.business.vm

import android.databinding.Bindable
import android.os.Bundle
import android.view.View
import io.plan8.business.BR
import io.plan8.business.activity.TaskActivity

/**
 * Created by SSozi on 2017. 11. 2..
 */
class TaskActivityVM(activity: TaskActivity, savedInstanceState: Bundle?) : ActivityVM(activity, savedInstanceState) {
    var selectedDate: String = ""
        get() {
            if (field.equals("")) {
                return "날짜변경"
            }
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
        }

    var toolbarTitle: String = ""
            @Bindable
            get() {
                return field
            }
        set (toolbarTitle) {
            field = toolbarTitle
            notifyPropertyChanged(BR.toolbarTitle)
        }

    fun changeDate(view: View) {
        isOpenedCalendar = !isOpenedCalendar
        if (isOpenedCalendar) {
            toolbarTitle = "날짜변경"
        } else {
            toolbarTitle = selectedDate;
        }
    }

    init {
        selectedDate = "오늘 날짜 들어가기"
    }
}