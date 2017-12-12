package io.plan8.backoffice.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.databinding.ActivitySplashBinding;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.vm.SplashActivityVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;
    private SplashActivityVM vm;
    private RelativeLayout progressBar;
    private SplashActivity self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        vm = new SplashActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

//        if (BuildConfig.DEBUG) {
//            hasTokenStep();
//        } else {
        if (!SharedPreferenceManager.getInstance().getUserToken(getApplicationContext()).equals("")) {
            String token = SharedPreferenceManager.getInstance().getUserToken(getApplicationContext());

            if (RestfulAdapter.getInstance().getServiceApi() != null) {
                Call<User> meCall = RestfulAdapter.getInstance().getServiceApi().getMe("Bearer " + token);
                meCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (user != null) {
                            ApplicationManager.getInstance().setUser(user);
                            hasTokenStep();
                        } else {
                            loginStep();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        MaterialDialog dialog = new MaterialDialog.Builder(self)
                                .content("문제가 발생했어요.\n잠시 후 다시 시도해주세요.")
                                .positiveText("닫기")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        finish();
                                    }
                                })
                                .build();
                        dialog.show();
                    }
                });
            }
        } else {
            loginStep();
        }
    }

    private void loginStep() {
        binding.splashProgressBarContainer.setVisibility(View.GONE);
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
        overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
    }

    private void hasTokenStep() {
        binding.splashProgressBarContainer.setVisibility(View.GONE);
        startActivity(MainActivity.buildIntent(this));
        finish();
        overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
    }
}
