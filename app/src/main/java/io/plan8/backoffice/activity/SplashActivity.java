package io.plan8.backoffice.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;
    private SplashActivityVM vm;
    private RelativeLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        vm = new SplashActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        if (SharedPreferenceManager(getApplicationContext()).userToken != "") {
            String token = SharedPreferenceManager(getApplicationContext()).userToken;

            if (RestfulAdapter.getInstance().serviceApi != null) {
                RestfulAdapter.getInstance().serviceApi.getMe("Bearer " + token);
//                        .enqueue(object :
//                Callback<Me> {
//                    override fun onResponse(call:Call<Me>?,response:
//                    Response<Me>?){
//                        if (response.body() != null) {
//                            Constants.me = response.body();
//                            hasTokenStep();
//                        } else {
//                            loginStep();
//                        }
//                    }
//                    override fun onFailure(call:Call<Me>?,t:
//                    Throwable ?){
//                        Toast.makeText(applicationContext, "잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
//                    }
//
//                })
            }
        } else {
            loginStep();
        }
    }

    private void loginStep() {
        progressBar.setVisibility(View.GONE);
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
        overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity)
    }

    private void hasTokenStep(){
        progressBar.setVisibility(View.GONE);
        startActivity(MainActivity.buildIntent(this));
        finish();
        overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity)
    }
}
