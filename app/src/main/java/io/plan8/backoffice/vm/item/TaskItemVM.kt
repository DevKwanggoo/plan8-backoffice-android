package io.plan8.backoffice.vm.item

import android.databinding.Bindable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import io.plan8.backoffice.R
import io.plan8.backoffice.activity.DetailTaskActivity
import io.plan8.backoffice.model.item.TaskItem
import io.plan8.backoffice.vm.FragmentVM

/**
 * Created by chokwanghwan on 2017. 11. 5..
 */
class TaskItemVM(var fragment: Fragment, savedInstanceState: Bundle?, var taskItem: TaskItem) : FragmentVM(fragment, savedInstanceState) {

    @Bindable
    fun getReservationtime(): String {
        return taskItem.reservationTime
    }

    @Bindable
    fun getCustomerName(): String {
        return taskItem.customerName
    }

    @Bindable
    fun getStatus(): String {
        return taskItem.status
    }

    @Bindable
    fun getCustomerAddress(): String {
        return taskItem.customerAddress
    }

    @Bindable
    fun getProductionName(): String {
        return taskItem.productionName
    }

    @Bindable
    fun getTaskStatus(): String {
        return taskItem.status
    }

    fun showDetailTask(view: View) {
        fragment.startActivity(DetailTaskActivity.buildIntent(context, taskItem))
        fragment.activity.overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity)
    }
}