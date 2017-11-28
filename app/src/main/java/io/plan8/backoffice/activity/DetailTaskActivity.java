package io.plan8.backoffice.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.ActivityDetailTaskBinding;

public class DetailTaskActivity extends BaseActivity {
    private ActivityDetailTaskBinding binding;
    private DetailTaskActivityVM vm;

    public static Intent buildIntent(Context context, TaskItem taskItem) {
        Intent intent = new Intent(context, DetailTaskActivity.class);
        intent.putExtra("taskItem", taskItem);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail.task);
        vm = DetailTaskActivityVM(this, savedInstanceState, getIntent().getSerializableExtra("taskItem"));
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();
    }

    @Override
    protected void onDestroy() {
        binding.unbind();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity)
    }
}
