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
import io.plan8.backoffice.model.api.ServerTime;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.vm.SplashActivityVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;
    private SplashActivityVM vm;
    private RelativeLayout progressBar;
    private SplashActivity self;
    private boolean serverTimeFlag = false;
    private boolean userFlag = false;
    private String serverTimeOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        vm = new SplashActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        if (!SharedPreferenceManager.getInstance().getUserToken(getApplicationContext()).equals("")) {
            if (RestfulAdapter.getInstance().getNeedTokenApiService() != null) {
                Call<ServerTime> getServerTime = RestfulAdapter.getInstance().getNeedTokenApiService().getServerTime(DateUtil.getInstance().getMilisecondsToTZFormat(DateUtil.getInstance().getCurrentDateLongFormat()));
                getServerTime.enqueue(new Callback<ServerTime>() {
                    @Override
                    public void onResponse(Call<ServerTime> call, Response<ServerTime> response) {
                        ServerTime serverTime = response.body();
                        serverTimeFlag = true;
                        if (serverTime != null) {
                            serverTimeOffset = serverTime.getOffset();
                            hasTokenStep();
                        } else {
                            loginStep();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerTime> call, Throwable t) {
                        serverTimeFlag = true;
                        loginStep();
                    }
                });

                Call<User> meCall = RestfulAdapter.getInstance().getNeedTokenApiService().getMe();
                meCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (user != null) {
                            ApplicationManager.getInstance().setUser(user);
                            userFlag = true;
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
                                        dialog.dismiss();
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
        if (serverTimeFlag && userFlag) {
            ApplicationManager.getInstance().setServerTimeOffset(serverTimeOffset);
            binding.splashProgressBarContainer.setVisibility(View.GONE);
            startActivity(MainActivity.buildIntent(this));
            finish();
            overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
        }
    }
}
