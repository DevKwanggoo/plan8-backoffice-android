package io.plan8.backoffice.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.ActivityEmptyTeamBinding;
import io.plan8.backoffice.vm.EmptyTeamActivityVM;

public class EmptyTeamActivity extends BaseActivity {
    private ActivityEmptyTeamBinding binding;
    private EmptyTeamActivityVM vm;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, EmptyTeamActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_empty_team);
        vm = new EmptyTeamActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
