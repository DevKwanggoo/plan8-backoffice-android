package io.plan8.backoffice.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.plan8.backoffice.BR
import io.plan8.backoffice.R
import io.plan8.backoffice.databinding.ActivityEditTaskBinding
import io.plan8.backoffice.model.item.TaskItem
import io.plan8.backoffice.vm.EditTaskActivityVM

class EditTaskActivity : BaseActivity() {
    companion object {
        fun buildIntent(context: Context): Intent {
            val intent = Intent(context, EditTaskActivity::class.java)
            return intent
        }
    }
    private lateinit var binding: ActivityEditTaskBinding
    private lateinit var vm: EditTaskActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_task)
        vm = EditTaskActivityVM(this, savedInstanceState)
        binding.setVariable(BR.vm, vm)
        binding.executePendingBindings()
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity)
    }
}
