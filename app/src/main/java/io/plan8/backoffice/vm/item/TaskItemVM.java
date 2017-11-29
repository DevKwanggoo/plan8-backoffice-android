package io.plan8.backoffice.vm.item;

import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.DetailTaskActivity;
import io.plan8.backoffice.model.item.TaskItem;
import io.plan8.backoffice.vm.FragmentVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class TaskItemVM extends FragmentVM {
    private TaskItem taskItem;
    public TaskItemVM(Fragment fragment, Bundle savedInstanceState, TaskItem taskItem) {
        super(fragment, savedInstanceState);
        this.taskItem = taskItem;
    }

    @Bindable
    public String getReservationTime() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getReservationTime();
    }

    @Bindable
    public String getCustomerName() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getCustomerName();
    }

    @Bindable
    public String getCustomerAddress() {
        if (null == taskItem) {
            return "";
        }

        return taskItem.getCustomerAddress();
    }

    @Bindable
    public String getProductionName() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getProductionName();
    }

    @Bindable
    public String getTaskStatus() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getStatus();
    }

    @Bindable
    public String getTaskCloseStatus() {
        if (null == taskItem) {
            return "";
        }
        return taskItem.getCloseStatus();
    }

    public void showDetailTask(View view) {
        getFragment().startActivity(DetailTaskActivity.buildIntent(getFragment().getContext(), taskItem));
        getFragment().getActivity().overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
    }
}
