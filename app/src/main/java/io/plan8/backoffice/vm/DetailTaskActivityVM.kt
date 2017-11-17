package io.plan8.backoffice.vm

import android.app.Activity
import android.content.Context
import android.databinding.Bindable
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import io.plan8.backoffice.BR
import io.plan8.backoffice.Constants
import io.plan8.backoffice.R
import io.plan8.backoffice.activity.EditTaskActivity
import io.plan8.backoffice.model.item.TaskItem

/**
 * Created by SSozi on 2017. 11. 5..
 */
class DetailTaskActivityVM(var activity: Activity, savedInstanceState: Bundle?, var taskItem: TaskItem) : ActivityVM(activity, savedInstanceState) {
    private var bottomSheet: BottomSheetDialog = BottomSheetDialog(activity)

    init {
        bottomSheet.setContentView(R.layout.bottom_sheet_layout)
        bottomSheet.findViewById<AppCompatImageView>(R.id.bottomSheetFirstIcon)!!.visibility = View.GONE
        bottomSheet.findViewById<TextView>(R.id.bottomSheetFirstTitle)!!.text = "완료"
        bottomSheet.findViewById<TextView>(R.id.bottomSheetFirstTitle)!!.setTextColor(ContextCompat.getColor(activity, R.color.taskStatusBlue))
        bottomSheet.findViewById<RelativeLayout>(R.id.bottomSheetFirstItem)!!.setOnClickListener {
            taskItem.status = Constants.TASK_STATUS_BLUE
            notifyPropertyChanged(BR.reservationStatus)
            bottomSheet.hide()
        }
        bottomSheet.findViewById<AppCompatImageView>(R.id.bottomSheetSecondIcon)!!.visibility = View.GONE
        bottomSheet.findViewById<TextView>(R.id.bottomSheetSecondTitle)!!.text = "미완료"
        bottomSheet.findViewById<TextView>(R.id.bottomSheetSecondTitle)!!.setTextColor(ContextCompat.getColor(activity, R.color.taskStatusRed))
        bottomSheet.findViewById<RelativeLayout>(R.id.bottomSheetSecondItem)!!.setOnClickListener {
            taskItem.status = Constants.TASK_STATUS_RED
            notifyPropertyChanged(BR.reservationStatus)
            bottomSheet.hide()
        }
    }

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

    @Bindable
    fun getReservationStatus(): String {
        return if (taskItem.status == Constants.TASK_STATUS_BLUE){
            "완료"
        } else {
            "미완료"
        }
    }

    fun finish(view: View) {
        activity.onBackPressed()
        activity.overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity)
    }

    fun openEditActivity(view: View){
        activity.startActivity(EditTaskActivity.buildIntent(activity))
        activity.overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity)
    }

    fun editTaskStatus(view: View){
        bottomSheet.show()
    }
}