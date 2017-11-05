package io.plan8.business.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import io.plan8.business.BR
import io.plan8.business.R
import io.plan8.business.databinding.ActivityDetailTaskBinding
import io.plan8.business.model.item.TaskItem
import io.plan8.business.vm.DetailTaskActivityVM

class DetailTaskActivity : BaseActivity() {
    var binding: ActivityDetailTaskBinding? = null
    var vm: DetailTaskActivityVM? = null

    companion object {
        fun buildIntent(context: Context, taskItem: TaskItem): Intent {
            val intent = Intent(context, DetailTaskActivity::class.java)
            intent.putExtra("taskItem", taskItem)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_task)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_task)
        vm = DetailTaskActivityVM(this, savedInstanceState, intent.getSerializableExtra("taskItem") as TaskItem)
        binding!!.setVariable(BR.vm, vm)
        binding!!.executePendingBindings()
    }

    override fun onDestroy() {
        binding!!.unbind()
        super.onDestroy()
    }
}
