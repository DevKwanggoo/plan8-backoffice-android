package io.plan8.backoffice.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import io.plan8.backoffice.model.api.Me;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_authorization);
        vm = new LoginAuthorizationActivityVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        String phoneNumber = getIntent().getStringExtra("phoneNumber");

        authoTitle = binding.authorizationTitle;
        authoTitle.setText("'" + phoneNumber + "' 번호로\n인증번호 문자메시지가 발송되었습니다.\n4자리 인증번호를 입력해주세요.");
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
                if (authoEditText.getText().length() >= 6) authStep();
            }
        });
        binding.authPrevStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final ArrayList<View> focusLineList = new ArrayList<>();
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
                            authStep();
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
                    firstInput.setText("");
                    secondInput.setText("");
                    thirdInput.setText("");
                    fourthInput.setText("");
                    fifthInput.setText("");
                    sixthInput.setText("");
                }
            }
        };


        authoEditText.addTextChangedListener(textWatcher);
        authoEditText.setFocusableInTouchMode(true);
        authoEditText.requestFocus();
        ViewUtil.getInstance().showKeyboard(authoEditText);
    }

    //TODO : 문자인증번호 파싱 리시버 로직임. 필요할때 주석제거
//    private fun registerSMSReceiver() {
//        val action = "android.provider.Telephony.SMS_RECEIVED"
//        val logTag = "SmsReceiver"
//
//        val intentFilter = IntentFilter()
//        intentFilter.addAction(action)
//        broadcastReceiver = object : BroadcastReceiver() {
//            override fun onReceive(context: Context, intent: Intent) {
//                if (intent.action == action) {
//                    //Bundel 널 체크
//                    val bundle = intent.extras ?: return
//
//                    //pdu 객체 널 체크
//                    val pdusObj = bundle.get("pdus") as Array<Any> ?: return
//
//                    //message 처리
//                    val smsMessages = arrayOfNulls<SmsMessage>(pdusObj.size)
//                    for (i in pdusObj.indices) {
//                        smsMessages[i] = SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
//                        Log.e(logTag, "NEW SMS " + i + "th")
//                        Log.e(logTag, "DisplayOriginatingAddress : " + smsMessages[i].getDisplayOriginatingAddress())
//                        Log.e(logTag, "DisplayMessageBody : " + smsMessages[i].getDisplayMessageBody())
//                        Log.e(logTag, "EmailBody : " + smsMessages[i].getEmailBody())
//                        Log.e(logTag, "EmailFrom : " + smsMessages[i].getEmailFrom())
//                        Log.e(logTag, "OriginatingAddress : " + smsMessages[i].getOriginatingAddress())
//                        Log.e(logTag, "MessageBody : " + smsMessages[i].getMessageBody())
//                        Log.e(logTag, "ServiceCenterAddress : " + smsMessages[i].getServiceCenterAddress())
//                        Log.e(logTag, "TimestampMillis : " + smsMessages[i].getTimestampMillis())
//
//                        val noSpaceStr = smsMessages[i].getMessageBody()
//                        noSpaceStr.replace("\\p{Z}", "")
//                        val authoNumber = smsMessages[i].getMessageBody().split("인증번호:".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].split("\\(3분간 유효합니다\\)".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
//
//                        if (authoNumber != null && (context.applicationContext as BaseApplication).getLoginAuthorizationActivity() != null) {
//                            progressBar!!.visibility = View.VISIBLE
//                            requestOAuth(authoNumber)
//                        }
//
//                        Log.e("message", smsMessages[i].getMessageBody())
//                    }
//                }
//            }
//        }
//
//        registerReceiver(broadcastReceiver, intentFilter)
//    }

    private void authStep() {
        ViewUtil.getInstance().hideKeyboard(authoEditText);
        progressBar.setVisibility(View.VISIBLE);

        Call<Auth> authCall = RestfulAdapter.getInstance().getServiceApi().getAuthIfo(getIntent().getStringExtra("code"), authoEditText.getText().toString());
        authCall.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.body() != null) {
                    SharedPreferenceManager.getInstance().setUserToken(getApplicationContext(), response.body().getToken());

                    Call<Me> meCall = RestfulAdapter.getInstance().getServiceApi().getMe("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getApplicationContext()));
                    meCall.enqueue(new Callback<Me>() {
                        @Override
                        public void onResponse(Call<Me> call, Response<Me> response) {
                            if (response.body() != null) {
                                ApplicationManager.getInstance().setMe(response.body());
                                nextActivity();
                            }
                        }

                        @Override
                        public void onFailure(Call<Me> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "인증번호를 확인 해주세요.", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                onBackPressed();
            }
        });
    }

    private void nextActivity() {
        progressBar.setVisibility(View.GONE);
        startActivity(MainActivity.buildIntent(this));
        finish();
        overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity);
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
            nextActivity();
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
}
