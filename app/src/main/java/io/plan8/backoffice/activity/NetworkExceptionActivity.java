package io.plan8.backoffice.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.ActivityNetworkExceptionBinding;
import io.plan8.backoffice.vm.NetworkExceptionActivityVM;

public class NetworkExceptionActivity extends BaseActivity {
    private ActivityNetworkExceptionBinding binding;
    private NetworkExceptionActivityVM vm;

    public static Intent buildIntent(Context context) {
        Intent intent = new Intent(context, NetworkExceptionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_network_exception);
        vm = new NetworkExceptionActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
