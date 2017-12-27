package io.plan8.backoffice.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.databinding.ActivityLoginAuthorizationBinding;
import io.plan8.backoffice.model.api.Auth;
import io.plan8.backoffice.model.api.ServerTime;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.util.ViewUtil;
import io.plan8.backoffice.vm.LoginAuthorizationActivityVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAuthorizationActivity extends BaseActivity implements TextView.OnEditorActionListener, View.OnClickListener {
    private ActivityLoginAuthorizationBinding binding;
    private LoginAuthorizationActivityVM vm;
    private TextView authoTitle;
    private RelativeLayout progressBar;
    private String userPhoneNumber;
    private BroadcastReceiver broadcastReceiver;
    private TextView firstInput;
    private TextView secondInput;
    private TextView thirdInput;
    private TextView fourthInput;
    private TextView fifthInput;
    private TextView sixthInput;
    private LinearLayout inputField;
    private EditText authoEditText;
    private boolean userFlag = false;
    private boolean serverTimeFlag = false;
    private ArrayList<View> focusLineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_authorization);
        vm = new LoginAuthorizationActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        String phoneNumber = getIntent().getStringExtra("phoneNumber");

        authoTitle = binding.authorizationTitle;
        authoTitle.setText("'" + phoneNumber + "' 번호로 발송된\n6자리 인증번호를 입력해주세요.");
        authoEditText = binding.authorizationCodeInputEditText;
        authoEditText.setOnEditorActionListener(this);

        progressBar = binding.loginAuthProgressBarContainer;

        firstInput = binding.firstInput;
        secondInput = binding.secondInput;
        thirdInput = binding.thirdInput;
        fourthInput = binding.fourthInput;
        fifthInput = binding.fifthInput;
        sixthInput = binding.sixthInput;
        inputField = binding.authoInputField;
        inputField.setOnClickListener(this);
        binding.authNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (authoEditText.getText().length() >= 6) authStep("");
            }
        });
        binding.authPrevStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (null == focusLineList) {
            focusLineList = new ArrayList<>();
        }
        focusLineList.add(binding.firstLine);
        focusLineList.add(binding.secondLine);
        focusLineList.add(binding.thirdLine);
        focusLineList.add(binding.fourthLine);
        focusLineList.add(binding.fifthLine);
        focusLineList.add(binding.sixthLine);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    int position = start - before;
                    if (position == -1) {
                        firstInput.setText("");
                        for (int i = 0; i < focusLineList.size(); i++) {
                            if (i == position + 1) {
                                focusLineList.get(position + 1).setVisibility(View.VISIBLE);
                            } else {
                                focusLineList.get(i).setVisibility(View.GONE);
                            }
                        }
                    } else if (position == 0) {
                        if (count == 4) {
                            firstInput.setText(s.charAt(0) + "");
                            secondInput.setText(s.charAt(1) + "");
                            thirdInput.setText(s.charAt(2) + "");
                            fourthInput.setText(s.charAt(3) + "");
                            for (int i = 0; i < focusLineList.size(); i++) {
                                focusLineList.get(i).setVisibility(View.GONE);
                            }
                        } else {
                            firstInput.setText(s.charAt(position) + "");
                            for (int i = 0; i < focusLineList.size(); i++) {
                                if (i == position + 1) {
                                    focusLineList.get(position + 1).setVisibility(View.VISIBLE);
                                } else {
                                    focusLineList.get(i).setVisibility(View.GONE);
                                }
                            }
                            secondInput.setText("");
                        }
                    } else if (position == 1) {
                        secondInput.setText(s.charAt(position) + "");
                        for (int i = 0; i < focusLineList.size(); i++) {
                            if (i == position + 1) {
                                focusLineList.get(position + 1).setVisibility(View.VISIBLE);
                            } else {
                                focusLineList.get(i).setVisibility(View.GONE);
                            }
                        }
                        thirdInput.setText("");
                    } else if (position == 2) {
                        thirdInput.setText(s.charAt(position) + "");
                        for (int i = 0; i < focusLineList.size(); i++) {
                            if (i == position + 1) {
                                focusLineList.get(position + 1).setVisibility(View.VISIBLE);
                            } else {
                                focusLineList.get(i).setVisibility(View.GONE);
                            }
                        }
                        fourthInput.setText("");
                    } else if (position == 3) {
                        fourthInput.setText(s.charAt(position) + "");
                        for (int i = 0; i < focusLineList.size(); i++) {
                            if (i == position + 1) {
                                focusLineList.get(position + 1).setVisibility(View.VISIBLE);
                            } else {
                                focusLineList.get(i).setVisibility(View.GONE);
                            }
                        }
                        fifthInput.setText("");
                    } else if (position == 4) {
                        fifthInput.setText(s.charAt(position) + "");
                        for (int i = 0; i < focusLineList.size(); i++) {
                            if (i == position + 1) {
                                focusLineList.get(position + 1).setVisibility(View.VISIBLE);
                            } else {
                                focusLineList.get(i).setVisibility(View.GONE);
                            }
                        }
                        sixthInput.setText("");
                    } else if (position == 5) {
                        sixthInput.setText(s.charAt(position) + "");
                        for (int i = 0; i < focusLineList.size(); i++) {
                            focusLineList.get(i).setVisibility(View.GONE);
                        }
                        if (s.length() == 6) {
                            authStep("");
                        }
                    }
                } else {
                    for (int i = 0; i < focusLineList.size(); i++) {
                        if (i == 0) {
                            focusLineList.get(i).setVisibility(View.VISIBLE);
                        } else {
                            focusLineList.get(i).setVisibility(View.GONE);
                        }
                    }
                    clearInput();
                }
            }
        };

        authoEditText.addTextChangedListener(textWatcher);
        authoEditText.setFocusableInTouchMode(true);
        authoEditText.requestFocus();
        ViewUtil.getInstance().showKeyboard(authoEditText);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_DENIED) {
            registerSMSReceiver();
        }
    }

    private void clearInput() {
        firstInput.setText("");
        secondInput.setText("");
        thirdInput.setText("");
        fourthInput.setText("");
        fifthInput.setText("");
        sixthInput.setText("");
    }

    //TODO : 문자 가이드 생기면 가이드대로 정규식 만들어야함.
    private void registerSMSReceiver() {
        final String action = "android.provider.Telephony.SMS_RECEIVED";
        final String logTag = "SmsReceiver";

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (null != intent.getAction()
                        && intent.getAction().equals(action)) {
                    //Bundel 널 체크
                    Bundle bundle = intent.getExtras();
                    if (bundle == null) {
                        return;
                    }

                    //pdu 객체 널 체크
                    Object[] pdusObj = (Object[]) bundle.get("pdus");
                    if (pdusObj == null) {
                        return;
                    }

                    //message 처리
                    SmsMessage[] smsMessages = new SmsMessage[pdusObj.length];
                    for (int i = 0; i < pdusObj.length; i++) {
                        smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        Log.e(logTag, "NEW SMS " + i + "th");
                        Log.e(logTag, "DisplayOriginatingAddress : "
                                + smsMessages[i].getDisplayOriginatingAddress());
                        Log.e(logTag, "DisplayMessageBody : "
                                + smsMessages[i].getDisplayMessageBody());
                        Log.e(logTag, "EmailBody : "
                                + smsMessages[i].getEmailBody());
                        Log.e(logTag, "EmailFrom : "
                                + smsMessages[i].getEmailFrom());
                        Log.e(logTag, "OriginatingAddress : "
                                + smsMessages[i].getOriginatingAddress());
                        Log.e(logTag, "MessageBody : "
                                + smsMessages[i].getMessageBody());
                        Log.e(logTag, "ServiceCenterAddress : "
                                + smsMessages[i].getServiceCenterAddress());
                        Log.e(logTag, "TimestampMillis : "
                                + smsMessages[i].getTimestampMillis());

                        String noSpaceStr = (smsMessages[i].getMessageBody());
                        noSpaceStr.replaceAll("\\p{Z}", "");
                        String authoNumber = (noSpaceStr.split("인증번호:")[1]).split("\\(3분간유효합니다\\)")[0];

                        if (authoNumber != null && !authoNumber.equals("") && authoNumber.length() == 6) {
                            authStep(authoNumber);
                        }

                        Log.e("message", smsMessages[i].getMessageBody());
                    }
                }

            }
        };
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void authStep(String authNumber) {
        ViewUtil.getInstance().hideKeyboard(authoEditText);
        progressBar.setVisibility(View.VISIBLE);

        if (authNumber == null || authNumber.equals("")) {
            authNumber = authoEditText.getText().toString();
        }

        Call<Auth> authCall = RestfulAdapter.getInstance().getApiService().getAuthIfo(getIntent().getStringExtra("code"), authNumber);
        authCall.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                Auth auth = response.body();
                if (auth != null) {
                    SharedPreferenceManager.getInstance().setUserToken(getApplicationContext(), auth.getToken());

                    Call<User> meCall = RestfulAdapter.getInstance().getNeedTokenApiService().getMe();
                    meCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.body() != null) {
                                ApplicationManager.getInstance().setUser(response.body());
                                userFlag = true;
                                nextStep(true);
                            } else {
                                nextStep(false);
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            nextStep(false);
                        }
                    });

                    Call<ServerTime> getServerTime = RestfulAdapter.getInstance().getNeedTokenApiService().getServerTime(DateUtil.getInstance().getMilisecondsToTZFormat(DateUtil.getInstance().getCurrentDateLongFormat()));
                    getServerTime.enqueue(new Callback<ServerTime>() {
                        @Override
                        public void onResponse(Call<ServerTime> call, Response<ServerTime> response) {
                            ServerTime serverTime = response.body();
                            if (serverTime != null && serverTime.getOffset() != null) {
                                ApplicationManager.getInstance().setServerTimeOffset(serverTime.getOffset());
                                serverTimeFlag = true;
                                nextStep(true);
                            } else {
                                nextStep(false);
                            }
                        }

                        @Override
                        public void onFailure(Call<ServerTime> call, Throwable t) {
                            nextStep(false);
                        }
                    });
                } else {
                    pleaseCheckAuthNumber();
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                pleaseCheckAuthNumber();
            }
        });
    }

    private void pleaseCheckAuthNumber() {
        Toast.makeText(getApplicationContext(), "인증번호를 확인 해주세요.", Toast.LENGTH_SHORT).show();
        onBackPressed();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
        overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
    }

    @Override
    public boolean onEditorAction(TextView v, int i, KeyEvent keyEvent) {
        if (v.getId() == authoEditText.getId() && i == EditorInfo.IME_ACTION_DONE) {
            nextStep(true);
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (authoEditText != null) {
            authoEditText.requestFocus();
            authoEditText.setSelection(authoEditText.getText().length());
            authoEditText.setFocusableInTouchMode(true);
            ViewUtil.getInstance().showKeyboard(authoEditText);
        }
    }

    public void nextStep(boolean isOnSuccess) {
        if (isOnSuccess) {
            if (userFlag && serverTimeFlag) {
                progressBar.setVisibility(View.GONE);
                startActivity(MainActivity.buildIntent(this));
                finish();
                overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
            }
        } else {
            Toast.makeText(getApplicationContext(), "잠시 후 다시 시도해주세요.1111", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            onBackPressed();
        }
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}