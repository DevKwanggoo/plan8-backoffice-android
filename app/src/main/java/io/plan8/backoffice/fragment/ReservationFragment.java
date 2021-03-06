package io.plan8.backoffice.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.BR;
import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.databinding.FragmentReservationBinding;
import io.plan8.backoffice.listener.EndlessRecyclerOnScrollListener;
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.ReservationFragmentVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class ReservationFragment extends BaseFragment {
    private FragmentReservationBinding binding;
    private ReservationFragmentVM vm;
    private String currentDate;
    private List<Reservation> reservations;
    private boolean editFlag = false;
    private Reservation editItem;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_reservation, container, false);
        vm = new ReservationFragmentVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore(int currentPage) {
                refreshReservationList();
            }
        };

        binding.reservationRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        binding.reservationRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reservations.clear();
                endlessRecyclerOnScrollListener.initPrevItemCount();
                refreshReservationList();
                binding.reservationRefreshLayout.setRefreshing(false);
            }
        });

        binding.reservationRecyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);

        binding.reservationCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                CalendarDay calendarDay = widget.getSelectedDate();
                if (null == calendarDay) {
                    vm.setSelectedDate(DateUtil.getInstance().getCurrentDate());
                    return;
                }
                vm.setSelectedDate(DateUtil.getInstance().dateToYYYYMd(date.getDate()));
                currentDate = DateUtil.getInstance().getCurrnetDateAPIFormat(date.getDate());
                reservations.clear();
                endlessRecyclerOnScrollListener.initPrevItemCount();
                vm.setOpenedCalendar(false);
                refreshReservationList();
            }
        });
        refreshReservationList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != binding) {
            binding.unbind();
        }
    }

    private void refreshReservationList() {
        vm.setCompletedLoading(false);
        if (null == reservations) {
            reservations = new ArrayList<>();
        }
        if (null == currentDate || currentDate.equals("")) {
            currentDate = DateUtil.getInstance().getCurrnetDateAPIFormat(Calendar.getInstance().getTime());
        }

        int userId = -1;
        if (null != ApplicationManager.getInstance().getUser()) {
            userId = ApplicationManager.getInstance().getUser().getId();
        }

        Call<List<Reservation>> getReservations = RestfulAdapter.getInstance().getNeedTokenApiService().getReservations(currentDate,
                currentDate,
                userId,
                Constants.PAGINATION_RESERVATION_COUNT,
                reservations.size());
        getReservations.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                List<Reservation> result = response.body();

                if (null != result) {
                    if (reservations.size() + result.size() > reservations.size()) {
                        reservations.addAll(result);
                    }
                }
                vm.setDatas(reservations);
                vm.setCompletedLoading(true);
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                vm.setDatas(new ArrayList<Reservation>());
                vm.setCompletedLoading(true);
            }
        });
    }

    public void setEditFlag(boolean flag) {
        editFlag = flag;
    }

    public void editItem(Reservation reservation) {
        editItem = reservation;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (editFlag) {
            editFlag = false;
            if (editItem != null) {
                for (int i = 0; i < reservations.size(); i++) {

                    if (reservations.get(i).getId() == editItem.getId()) {
                        reservations.set(i, editItem);
                    }
                }
            }
            vm.setDatas(reservations);
        }
    }

    public void setSwipeFlag(boolean flag) {
        vm.setSwipeFlag(flag);
    }
}