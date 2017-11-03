package io.plan8.business.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.DatePicker
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import io.plan8.business.BR
import io.plan8.business.R
import io.plan8.business.databinding.ActivityTaskBinding
import io.plan8.business.vm.TaskActivityVM


class TaskActivity : BaseActivity() {
    var binding: ActivityTaskBinding? = null
    var vm: TaskActivityVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_task)
        vm = TaskActivityVM(this, savedInstanceState)
        binding!!.setVariable(BR.vm, vm)
        binding!!.executePendingBindings()

        binding!!.taskCalendarView.setOnDateChangedListener(object : DatePicker.OnDateChangedListener, OnDateSelectedListener {
            override fun onDateChanged(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                MyUtils().hideKeyboard(applicationContext, recyclerView!!.windowToken)
//            }
//        })
    }
}