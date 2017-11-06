package io.plan8.business.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.plan8.business.BR
import io.plan8.business.R
import io.plan8.business.databinding.ActivityScheduleInfoBinding
import io.plan8.business.vm.ScheduleInfoActivityVM

class ScheduleInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleInfoBinding
    private lateinit var vm: ScheduleInfoActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule_info)
        vm = ScheduleInfoActivityVM(this, savedInstanceState)
        binding.setVariable(BR.vm, vm)
        binding.executePendingBindings()
    }
}
