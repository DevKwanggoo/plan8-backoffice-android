package io.plan8.business.vm.item

import android.databinding.Bindable
import android.os.Bundle
import android.view.View
import io.plan8.business.activity.DetailTaskActivity
import io.plan8.business.activity.TaskActivity
import io.plan8.business.model.item.TaskItem
import io.plan8.business.vm.ActivityVM

/**
 * Created by chokwanghwan on 2017. 11. 5..
 */
class TaskItemVM(activity: TaskActivity, savedInstanceState: Bundle?, var taskItem: TaskItem) : ActivityVM(activity, savedInstanceState) {

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
        activity.startActivity(DetailTaskActivity.buildIntent(context, taskItem))
    }
}