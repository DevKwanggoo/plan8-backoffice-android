package io.plan8.business.vm.item

import android.content.Context
import android.databinding.Bindable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.plan8.business.model.item.TaskItem
import io.plan8.business.vm.BaseVM

/**
 * Created by chokwanghwan on 2017. 11. 5..
 */
class TaskItemVM(context: Context, savedInstanceState: Bundle?, var taskItem: TaskItem) : BaseVM(context, savedInstanceState) {

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
        Toast.makeText(context,"상세가기", Toast.LENGTH_SHORT).show()
    }
}