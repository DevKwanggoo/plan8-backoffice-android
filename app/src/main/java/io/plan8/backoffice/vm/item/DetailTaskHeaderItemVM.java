package io.plan8.backoffice.vm.item;

import android.app.Activity;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.Constants;
import io.plan8.backoffice.activity.DetailTaskActivity;
import io.plan8.backoffice.model.item.TaskItem;
import io.plan8.backoffice.vm.ActivityVM;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class DetailTaskHeaderItemVM extends ActivityVM {
    private TaskItem taskItem;

    public DetailTaskHeaderItemVM(Activity activity, Bundle savedInstanceState, TaskItem taskItem) {
        super(activity, savedInstanceState);
        this.taskItem = taskItem;
    }

    @Bindable
    public String getCustomerName() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getCustomerName();
    }

    @Bindable
    public String getCustomerPhoneNumber() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getCustomerPhoneNumber();
    }

    @Bindable
    public String getCustomerAddress() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getCustomerAddress();
    }

    @Bindable
    public String getReservationDate() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getReservationDate();
    }

    @Bindable
    public String getReservationTime() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getReservationTime();
    }

    @Bindable
    public String getReservationEndTime() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getReservationEndTime();
    }

    @Bindable
    public String getProductionName() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getProductionName();
    }

    @Bindable
    public String getCustomerRequest() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getCustomerRequest();
    }

    @Bindable
    public String getProductionDescription() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getProductionDescription();
    }

    @Bindable
    public String getReservationStatus() {
        if (null != taskItem && taskItem.equals(Constants.TASK_STATUS_BLUE)) {
            return "완료";
        } else {
            return "미완료";
        }
    }

    public void editTaskStatus(View view) {
        if (getActivity() instanceof DetailTaskActivity) {
            ((DetailTaskActivity) getActivity()).showBottomSheet();
        }
    }
}
