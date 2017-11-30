package io.plan8.backoffice.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.ActivityPreviewBinding;
import io.plan8.backoffice.vm.PreviewActivityVM;

public class PreviewActivity extends AppCompatActivity {
    private ActivityPreviewBinding binding;
    private PreviewActivityVM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_preview);
        vm = new PreviewActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();
    }
}
