package io.plan8.backoffice.vm;

import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.activity.BaseActivity;
import io.plan8.backoffice.databinding.ActivityLoginBinding;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class LoginActivityVM extends ActivityVM {
    private ActivityLoginBinding binding;

    public LoginActivityVM(BaseActivity activity, Bundle savedInstanceState, ActivityLoginBinding binding) {
        super(activity, savedInstanceState);
        this.binding = binding;
    }

    public void deletePhoneNumber(View view){
        if (!binding.loginPhoneNumber.getText().toString().equals("") && binding.loginPhoneNumber.getText().length() != 0){
            binding.loginPhoneNumber.setText("");
        }
    }
}
