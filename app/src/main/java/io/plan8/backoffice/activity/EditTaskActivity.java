package io.plan8.backoffice.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.ActivityEditTaskBinding;

public class EditTaskActivity extends BaseActivity {
    private ActivityEditTaskBinding binding;
    private EditTaskActivityVM vm;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, EditTaskActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_task);
        vm = EditTaskActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();
    }

    @Override
    public void onBackPressed() {
        finish()
        overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity)
    }
}
