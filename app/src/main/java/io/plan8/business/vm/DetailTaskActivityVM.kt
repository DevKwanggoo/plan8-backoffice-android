package io.plan8.business.vm

import android.app.Activity
import android.databinding.Bindable
import android.os.Bundle
import android.view.View
import io.plan8.business.R
import io.plan8.business.model.item.TaskItem

/**
 * Created by SSozi on 2017. 11. 5..
 */
class DetailTaskActivityVM(activity: Activity, savedInstanceState: Bundle?, var taskItem: TaskItem) : ActivityVM(activity, savedInstanceState) {
    @Bindable
    fun getCustomerName(): String {
        return taskItem.customerName
    }

    @Bindable
    fun getCustomerPhoneNumber(): String {
        return taskItem.customerPhoneNumber
    }

    @Bindable
    fun getCustomerAddress(): String {
        return taskItem.customerAddress
    }

    @Bindable
    fun getReservationDate(): String {
        return taskItem.reservationDate
    }

    @Bindable
    fun getReservationTime(): String {
        return taskItem.reservationTime
    }

    @Bindable
    fun getReservationEndTime(): String {
        return taskItem.reservationEndTime
    }

    @Bindable
    fun getProductionName(): String {
        return taskItem.productionName
    }

    @Bindable
    fun getCustomerRequest(): String {
        return taskItem.customerRequest
    }

    @Bindable
    fun getProductionDescription(): String {
        return taskItem.productionDescription
    }

    fun finish(view: View) {
        activity.onBackPressed()
        activity.overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity)
    }
}