package io.plan8.business.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.DatePicker
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import io.plan8.business.BR
import io.plan8.business.Constants
import io.plan8.business.R
import io.plan8.business.databinding.ActivityTaskBinding
import io.plan8.business.model.item.TaskItem
import io.plan8.business.util.DateUtil
import io.plan8.business.vm.TaskActivityVM


class TaskActivity : BaseActivity() {
    var binding: ActivityTaskBinding? = null
    var vm: TaskActivityVM? = null

    companion object {
        fun buildIntent(context: Context): Intent {
            return Intent(context, TaskActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val testData: MutableList<TaskItem> = mutableListOf<TaskItem>()
//        이름, 전화번호, 주소, 예약일, 예약시간, 종료시간, 상품/이름, 추가 요청 사항, 내부 노트
        testData.add(TaskItem("이해찬", "+821020851422", "서울특별시 강남구 역삼로 147, 3층 (역삼동 751-12, 인덕빌딩)", "2017년 11월 3일", "오후 11:30", "오후 11:50", "iPhone 5s / 홈버튼 파손 (30,000원)", "저는 빨간색 아이폰이에요", "고객님이 빠르게 와달라고 했습니다. 알아서 빨리 가세요", Constants.TASK_STATUS_BLUE))
        testData.add(TaskItem("이해찬", "+821020851422", "서울특별시 강남구 역삼로 147, 3층 (역삼동 751-12, 인덕빌딩)", "2017년 11월 3일", "오후 11:30", "오후 11:50", "iPhone 5s / 홈버튼 파손 (30,000원)", "저는 빨간색 아이폰이에요", "고객님이 빠르게 와달라고 했습니다. 알아서 빨리 가세요", Constants.TASK_STATUS_BLUE))
        testData.add(TaskItem("이해찬", "+821020851422", "서울특별시 강남구 역삼로 147, 3층 (역삼동 751-12, 인덕빌딩)", "2017년 11월 3일", "오후 11:30", "오후 11:50", "iPhone 5s / 홈버튼 파손 (30,000원)", "저는 빨간색 아이폰이에요", "고객님이 빠르게 와달라고 했습니다. 알아서 빨리 가세요", Constants.TASK_STATUS_BLUE))
        testData.add(TaskItem("이해찬", "+821020851422", "서울특별시 강남구 역삼로 147, 3층 (역삼동 751-12, 인덕빌딩)", "2017년 11월 3일", "오후 11:30", "오후 11:50", "iPhone 5s / 홈버튼 파손 (30,000원)", "저는 빨간색 아이폰이에요", "고객님이 빠르게 와달라고 했습니다. 알아서 빨리 가세요", Constants.TASK_STATUS_ORANGE))
        testData.add(TaskItem("이해찬", "+821020851422", "서울특별시 강남구 역삼로 147, 3층 (역삼동 751-12, 인덕빌딩)", "2017년 11월 3일", "오후 11:30", "오후 11:50", "iPhone 5s / 홈버튼 파손 (30,000원)", "저는 빨간색 아이폰이에요", "고객님이 빠르게 와달라고 했습니다. 알아서 빨리 가세요", Constants.TASK_STATUS_RED))

        binding = DataBindingUtil.setContentView(this, R.layout.activity_task)
        vm = TaskActivityVM(this, savedInstanceState, testData)
        binding!!.setVariable(BR.vm, vm)
        binding!!.executePendingBindings()

        binding!!.taskCalendarView.setOnDateChangedListener(object : DatePicker.OnDateChangedListener, OnDateSelectedListener {
            override fun onDateChanged(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
                val date = widget.selectedDate
                if (null == date) {
                    vm!!.selectedDate = DateUtil.getCurrentDate()
                }
                vm!!.selectedDate = DateUtil.dateToYYYYMd(date.date)
                vm!!.isOpenedCalendar = false
            }
        })
    }

    override fun onDestroy() {
        binding!!.unbind()
        super.onDestroy()
    }
}