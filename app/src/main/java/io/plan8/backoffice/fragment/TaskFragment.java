package io.plan8.backoffice.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.FragmentTaskBinding;
import io.plan8.backoffice.model.item.TaskItem;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.TaskFragmentVM;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class TaskFragment extends BaseFragment {
    private FragmentTaskBinding binding;
    private TaskFragmentVM vm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        List<TaskItem> testData = new ArrayList<>();
        //        이름, 전화번호, 주소, 예약일, 예약시간, 종료시간, 상품/이름, 추가 요청 사항, 내부 노트
        testData.add(new TaskItem("이해찬", "+821020851422", "서울특별시 강남구 역삼로 147, 3층 (역삼동 751-12, 인덕빌딩)", "2017년 11월 3일", "오후 11:30", "오후 11:50", "iPhone 5s / 홈버튼 파손 (30,000원)", "저는 빨간색 아이폰이에요", "고객님이 빠르게 와달라고 했습니다. 알아서 빨리 가세요", Constants.TASK_STATUS_BLUE, Constants.TASK_STATUS_GREEN));
        testData.add(new TaskItem("이해찬", "+821020851422", "서울특별시 강남구 역삼로 147, 3층 (역삼동 751-12, 인덕빌딩)", "2017년 11월 3일", "오후 11:30", "오후 11:50", "iPhone 5s / 홈버튼 파손 (30,000원)", "저는 빨간색 아이폰이에요", "고객님이 빠르게 와달라고 했습니다. 알아서 빨리 가세요", Constants.TASK_STATUS_BLUE, ""));
        testData.add(new TaskItem("이해찬", "+821020851422", "서울특별시 강남구 역삼로 147, 3층 (역삼동 751-12, 인덕빌딩)", "2017년 11월 3일", "오후 11:30", "오후 11:50", "iPhone 5s / 홈버튼 파손 (30,000원)", "저는 빨간색 아이폰이에요", "고객님이 빠르게 와달라고 했습니다. 알아서 빨리 가세요", Constants.TASK_STATUS_BLUE, ""));
        testData.add(new TaskItem("이해찬", "+821020851422", "서울특별시 강남구 역삼로 147, 3층 (역삼동 751-12, 인덕빌딩)", "2017년 11월 3일", "오후 11:30", "오후 11:50", "iPhone 5s / 홈버튼 파손 (30,000원)", "저는 빨간색 아이폰이에요", "고객님이 빠르게 와달라고 했습니다. 알아서 빨리 가세요", "", ""));
        testData.add(new TaskItem("이해찬", "+821020851422", "서울특별시 강남구 역삼로 147, 3층 (역삼동 751-12, 인덕빌딩)", "2017년 11월 3일", "오후 11:30", "오후 11:50", "iPhone 5s / 홈버튼 파손 (30,000원)", "저는 빨간색 아이폰이에요", "고객님이 빠르게 와달라고 했습니다. 알아서 빨리 가세요", Constants.TASK_STATUS_RED, ""));

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_task, container, false);
        vm = new TaskFragmentVM(this, savedInstanceState, testData);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.taskCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                CalendarDay calendarDay = widget.getSelectedDate();
                if (null == calendarDay) {
                    vm.setSelectedDate(DateUtil.getInstance().getCurrentDate());
                    return;
                }
                vm.setSelectedDate(DateUtil.getInstance().dateToYYYYMd(date.getDate()));
                vm.setOpenedCalendar(false);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
