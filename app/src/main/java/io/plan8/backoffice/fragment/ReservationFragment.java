package io.plan8.backoffice.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.databinding.FragmentReservationBinding;
import io.plan8.backoffice.model.api.Reservation;
import io.plan8.backoffice.model.api.Worker;
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
        binding.reservationCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                CalendarDay calendarDay = widget.getSelectedDate();
                if (null == calendarDay) {
                    vm.setSelectedDate(DateUtil.getInstance().getCurrentDate());
                    return;
                }
                vm.setSelectedDate(DateUtil.getInstance().dateToYYYYMd(date.getDate()));
                vm.setOpenedCalendar(false);
                refreshReservationList();
            }
        });

        refreshReservationList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void refreshReservationList() {
        Call<List<Reservation>> getReservations = RestfulAdapter.getInstance().getServiceApi().getReservations("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getContext()), ApplicationManager.getInstance().getCurrentTeam().getTeamId());
        getReservations.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                List<Reservation> reservations = response.body();
                List<Reservation> result = new ArrayList<>();
                if (null != reservations) {
                    for (Reservation r : reservations) {
                        if (null != r.getWorkers()) {
                            for (Worker w : r.getWorkers()) {
                                if (null != w
                                        && null != ApplicationManager.getInstance().getMe()
//                                        && w.getId() == ApplicationManager.getInstance().getMe().getId()) {
                                        && w.getId() == 1) {
                                    result.add(r);
                                }
                            }
                        }
                    }
                }

                vm.setDatas(result);
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                Log.e("api : ", "failure");
            }
        });
    }
}