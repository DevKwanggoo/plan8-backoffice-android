package io.plan8.backoffice.vm;

import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter;
import io.plan8.backoffice.model.item.TaskItem;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.item.TaskItemVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class TaskFragmentVM extends FragmentVM {
    private List<TaskItem> taskItems;
    private String selectedDate;
    private boolean isOpenedCalendar;
    private String toolbarTitle;
    private BindingRecyclerViewAdapter<TaskItem> adapter;
    private boolean emptyFlag;


    public TaskFragmentVM(final Fragment fragment, final Bundle savedInstanceState, List<TaskItem> taskItems) {
        super(fragment, savedInstanceState);
        this.taskItems = taskItems;
        selectedDate = DateUtil.getInstance().getCurrentDate();
        adapter = new BindingRecyclerViewAdapter<TaskItem>() {
            @Override
            protected int selectViewLayoutType(TaskItem data) {
                return R.layout.item_task;
            }

            @Override
            protected void bindVariables(ViewDataBinding binding, TaskItem data) {
                TaskItemVM taskItemVM = new TaskItemVM(fragment, savedInstanceState, data);
                binding.setVariable(BR.vm, taskItemVM);
            }
        };

        setDatas(taskItems);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getFragment().getContext());
    }

    public void setDatas(List<TaskItem> taskItems) {
        this.taskItems = taskItems;
        adapter.setData(taskItems);
    }

    @Bindable
    public Boolean getOpenedCalendar() {
        return isOpenedCalendar;
    }

    public void setOpenedCalendar(boolean isOpenedCalendar) {
        this.isOpenedCalendar = isOpenedCalendar;
        notifyPropertyChanged(BR.openedCalendar);
        notifyPropertyChanged(BR.toolbarTitle);

    }

    @Bindable
    public String getToolbarTitle() {
        return toolbarTitle;
    }

    public void setToolbarTitle(String toolbarTitle) {
        this.toolbarTitle = toolbarTitle;
        notifyPropertyChanged(BR.toolbarTitle);
    }

    @Bindable
    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
        setToolbarTitle(selectedDate);
        notifyPropertyChanged(BR.selectedDate);
    }

    public BindingRecyclerViewAdapter<TaskItem> getAdapter() {
        return adapter;
    }

    @Bindable
    public boolean getEmptyFlag() {
        return emptyFlag;
    }

    public void setEmptyFlag(boolean emptyFlag) {
        this.emptyFlag = emptyFlag;
        notifyPropertyChanged(BR.emptyFlag);
    }

    public void changeDate(View view) {
        setOpenedCalendar(!isOpenedCalendar);
    }
}
