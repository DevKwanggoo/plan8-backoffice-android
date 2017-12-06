package io.plan8.backoffice.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.ActivityEditReservationBinding;
import io.plan8.backoffice.vm.EditReservationActivityVM;

public class EditReservationActivity extends BaseActivity {
    private ActivityEditReservationBinding binding;
    private EditReservationActivityVM vm;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, EditReservationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_reservation);
        vm = new EditReservationActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
    }
}
