package io.plan8.business.vm

import android.databinding.Bindable
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import io.plan8.business.BR
import io.plan8.business.R
import io.plan8.business.activity.TaskActivity
import io.plan8.business.adapter.BindingRecyclerViewAdapter
import io.plan8.business.model.item.TaskItem
import io.plan8.business.util.DateUtil
import io.plan8.business.vm.item.TaskItemVM

/**
 * Created by SSozi on 2017. 11. 2..
 */
open class TaskActivityVM(activity: TaskActivity
                          , savedInstanceState: Bundle?, taskItemList: List<TaskItem>) : ActivityVM(activity, savedInstanceState) {
    var data: List<TaskItem>? = null
    var selectedDate: String = ""
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

    var adapter: BindingRecyclerViewAdapter<TaskItem>

    fun changeDate(view: View) {
        isOpenedCalendar = !isOpenedCalendar
    }

    init {
        selectedDate = DateUtil.getCurrentDate()
        adapter = object : BindingRecyclerViewAdapter<TaskItem>() {

            override fun selectViewLayoutType(data: TaskItem): Int {
                return R.layout.item_task
            }

            override fun bindVariables(binding: ViewDataBinding, data: TaskItem) {
                val taskItemVM = TaskItemVM(activity, savedInstanceState, data)
                binding.setVariable(BR.vm, taskItemVM)
            }
        }

        setDatas(taskItemList)
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    fun setDatas(data: List<TaskItem>?) {
        this.data = data
        adapter.data = this.data
    }
}