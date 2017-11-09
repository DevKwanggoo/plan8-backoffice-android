package io.plan8.backoffice.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import io.plan8.backoffice.BR
import io.plan8.backoffice.R
import io.plan8.backoffice.databinding.ActivityDetailTaskBinding
import io.plan8.backoffice.model.item.TaskItem
import io.plan8.backoffice.vm.DetailTaskActivityVM

class DetailTaskActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailTaskBinding
    private lateinit var vm: DetailTaskActivityVM

    companion object {
        fun buildIntent(context: Context, taskItem: TaskItem): Intent {
            val intent = Intent(context, DetailTaskActivity::class.java)
            intent.putExtra("taskItem", taskItem)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_task)
        vm = DetailTaskActivityVM(this, savedInstanceState, intent.getSerializableExtra("taskItem") as TaskItem)
        binding.setVariable(BR.vm, vm)
        binding.executePendingBindings()
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity)
    }
}
