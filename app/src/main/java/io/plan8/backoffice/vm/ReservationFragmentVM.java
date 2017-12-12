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
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.item.ReservationItemVM;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ReservationFragmentVM extends FragmentVM {
    private String selectedDate;
    private boolean isOpenedCalendar;
    private String toolbarTitle;
    private BindingRecyclerViewAdapter<Reservation> adapter;
    private boolean emptyFlag = true;

    public ReservationFragmentVM(final Fragment fragment, final Bundle savedInstanceState) {
        super(fragment, savedInstanceState);
        selectedDate = DateUtil.getInstance().getCurrentDate();
        adapter = new BindingRecyclerViewAdapter<Reservation>() {
            @Override
            protected int selectViewLayoutType(Reservation data) {
                return R.layout.item_reservation;
            }

            @Override
            protected void bindVariables(ViewDataBinding binding, Reservation data) {
                ReservationItemVM reservationItemVM = new ReservationItemVM(fragment, savedInstanceState, data);
                binding.setVariable(BR.vm, reservationItemVM);
            }
        };
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getFragment().getContext());
    }

    public void setDatas(List<Reservation> reservations) {
        if (reservations.size() <= 0) {
            setEmptyFlag(true);
        } else {
            setEmptyFlag(false);
        }
        adapter.setData(reservations);
    }

//    public void addDatas(List<Reservation> reservations){
//        adapter.addData(reservations, );
//    }

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
        if (toolbarTitle != null && !toolbarTitle.equals("")) {
            return toolbarTitle;
        }
        return DateUtil.getInstance().getCurrentDate();
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

    public BindingRecyclerViewAdapter<Reservation> getAdapter() {
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
