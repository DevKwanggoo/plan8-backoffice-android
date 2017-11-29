package io.plan8.backoffice.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.databinding.ActivityLoginBinding;
import io.plan8.backoffice.model.api.Login;
import io.plan8.backoffice.util.NetworkUtil;
import io.plan8.backoffice.vm.LoginActivityVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements TextView.OnEditorActionListener {
    private ActivityLoginBinding binding;
    private LoginActivityVM vm;
    private RelativeLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        vm = new LoginActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        progressBar = binding.loginProgressBarContainer;
        binding.loginPhoneNumber.setOnEditorActionListener(this);
        binding.loginNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep();
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int i, KeyEvent keyEvent) {
        if (v.getId() == binding.loginPhoneNumber.getId() && i == EditorInfo.IME_ACTION_DONE) {
            nextStep();
        }
        return false;
    }

    private void nextStep() {
        final String phoneNumber = binding.loginPhoneNumber.getText().toString();

        if (!phoneNumber.equals("") && phoneNumber.length() > 9) {
            if (!NetworkUtil.getInstance().checkInternetConnection(getApplicationContext())) {
                Toast.makeText(this, "휴대전화번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
            } else {
                Call<Login> pincodeCall = RestfulAdapter.getInstance().getServiceApi().getPinCode(phoneNumber);
                pincodeCall.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        Login login = response.body();
                        if (null != login) {
                            nextActivity(phoneNumber, login.getCode());
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(this, "휴대전화번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    private void nextActivity(String phoneNumber, String code) {
        Intent intent = new Intent(this, LoginAuthorizationActivity.class);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("code", code);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
    }
}
