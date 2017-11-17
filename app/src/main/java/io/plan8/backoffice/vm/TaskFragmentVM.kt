package io.plan8.backoffice.vm

import android.content.Intent
import android.databinding.Bindable
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import io.plan8.backoffice.BR
import io.plan8.backoffice.R
import io.plan8.backoffice.activity.EditTaskActivity
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter
import io.plan8.backoffice.model.item.TaskItem
import io.plan8.backoffice.util.DateUtil
import io.plan8.backoffice.vm.item.TaskItemVM

/**
 * Created by SSozi on 2017. 11. 2..
 */
open class TaskFragmentVM(var fragment: Fragment
                          , savedInstanceState: Bundle?, taskItemList: List<TaskItem>) : FragmentVM(fragment, savedInstanceState) {
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
            return selectedDate
        }
        set (toolbarTitle) {
            field = toolbarTitle
            notifyPropertyChanged(BR.toolbarTitle)
        }

    var adapter: BindingRecyclerViewAdapter<TaskItem>

    open var emptyFlag: Boolean = false
        @Bindable
        get() {
            return field
        }
        set(flag) {
        field = flag
        notifyPropertyChanged(BR.emptyFlag)
    }

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
                val taskItemVM = TaskItemVM(fragment, savedInstanceState, data)
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