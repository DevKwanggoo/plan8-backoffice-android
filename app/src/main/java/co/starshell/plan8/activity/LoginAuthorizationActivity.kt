package co.starshell.plan8.activity

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import co.starshell.plan8.BR
import co.starshell.plan8.R
import co.starshell.plan8.databinding.ActivityLoginAuthorizationBinding
import co.starshell.plan8.vm.LoginAuthorizationActivityVM
import java.util.*

class LoginAuthorizationActivity : BaseActivity(), TextView.OnEditorActionListener, View.OnClickListener {
    private lateinit var binding: ActivityLoginAuthorizationBinding
    private var vm: LoginAuthorizationActivityVM? = null
    private lateinit var authoTitle: TextView
    private var progressBar: RelativeLayout? = null
    private var userPhoneNumber: String? = null
    private var broadcastReceiver: BroadcastReceiver? = null
    private var firstInput: TextView? = null
    private var secondInput: TextView? = null
    private var thirdInput: TextView? = null
    private var fourthInput: TextView? = null
    private var inputField: LinearLayout? = null
    private var authoEditText: EditText? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_authorization)
        vm = LoginAuthorizationActivityVM(this, savedInstanceState)
        binding.setVariable(BR.vm, vm)
        binding.executePendingBindings()

        val phoneNumber = intent.getStringExtra("phoneNumber")

        authoTitle = binding.authorizationTitle
        authoTitle.text = "'$phoneNumber’ 번호로\n인증번호 문자메시지가 발송되었습니다.\n4자리 인증번호를 입력해주세요."
        authoEditText = binding.authorizationCodeInputEditText
        authoEditText!!.setOnEditorActionListener(this)

        progressBar = binding.loginAuthProgressBarContainer

        firstInput = binding.firstInput
        secondInput = binding.secondInput
        thirdInput = binding.thirdInput
        fourthInput = binding.fourthInput
        inputField = binding.authoInputField
        inputField!!.setOnClickListener(this)

        val focusLineList = ArrayList<View>()
        focusLineList.add(binding.firstLine)
        focusLineList.add(binding.secondLine)
        focusLineList.add(binding.thirdLine)
        focusLineList.add(binding.fourthLine)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(edit: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length > 0) {
                    val position = start - before
                    if (position == -1) {
                        firstInput!!.text = ""
                        for (i in focusLineList.indices) {
                            if (i == position + 1) {
                                focusLineList[position + 1].visibility = View.VISIBLE
                            } else {
                                focusLineList[i].visibility = View.GONE
                            }
                        }
                    } else if (position == 0) {
                        if (count == 4) {
                            firstInput!!.text = s[0] + ""
                            secondInput!!.text = s[1] + ""
                            thirdInput!!.text = s[2] + ""
                            fourthInput!!.text = s[3] + ""
                            for (i in focusLineList.indices) {
                                focusLineList[i].visibility = View.GONE
                            }
                        } else {
                            firstInput!!.text = s[position] + ""
                            for (i in focusLineList.indices) {
                                if (i == position + 1) {
                                    focusLineList[position + 1].visibility = View.VISIBLE
                                } else {
                                    focusLineList[i].visibility = View.GONE
                                }
                            }
                            secondInput!!.text = ""
                        }
                    } else if (position == 1) {
                        secondInput!!.text = s[position] + ""
                        for (i in focusLineList.indices) {
                            if (i == position + 1) {
                                focusLineList[position + 1].visibility = View.VISIBLE
                            } else {
                                focusLineList[i].visibility = View.GONE
                            }
                        }
                        thirdInput!!.text = ""
                    } else if (position == 2) {
                        thirdInput!!.text = s[position] + ""
                        for (i in focusLineList.indices) {
                            if (i == position + 1) {
                                focusLineList[position + 1].visibility = View.VISIBLE
                            } else {
                                focusLineList[i].visibility = View.GONE
                            }
                        }
                        fourthInput!!.text = ""
                    } else if (position == 3) {
                        fourthInput!!.text = s[position] + ""
                        for (i in focusLineList.indices) {
                            focusLineList[i].visibility = View.GONE
                        }
                        if (s.length == 4) {
//                            requestOAuth(s.toString())
                            nextStep(authoEditText!!)
                        }
                    }
                } else {
                    for (i in focusLineList.indices) {
                        if (i == 0) {
                            focusLineList[i].visibility = View.VISIBLE
                        } else {
                            focusLineList[i].visibility = View.GONE
                        }
                    }
                    firstInput!!.text = ""
                    secondInput!!.text = ""
                    thirdInput!!.text = ""
                    fourthInput!!.text = ""
                }
            }
        }

        authoEditText!!.addTextChangedListener(textWatcher)

        authoEditText!!.isFocusableInTouchMode = true
        authoEditText!!.requestFocus()
        showKeyboard(authoEditText!!)


//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_DENIED) {
//            registerSMSReceiver()
//        }

    }

    //TODO : 문자인증 로직 필요할때 주석제거
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

    fun nextStep(view: View) {
        //        progressBar.setVisibility(View.VISIBLE);
        hideKeyboard(authoEditText!!)
        nextActivity()
    }

    private fun nextActivity() {
        progressBar!!.visibility = View.GONE
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
        overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity)
    }

    override fun onBackPressed() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
        overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity)
    }

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
        if (v.id == authoEditText!!.id && actionId == EditorInfo.IME_ACTION_DONE) {
            nextStep(v)
        }
        return false
    }

//    override fun onDestroy() {
//        (applicationContext as BaseApplication).setLoginAuthorizationActivity(null)
//        super.onDestroy()
//    }


    override fun onClick(v: View) {
        if (authoEditText != null) {
            //            authoEditText.post(new Runnable() {
            //                @Override
            //                public void run() {
            //                    authoEditText.setFocusableInTouchMode(true);
            //                    authoEditText.requestFocus();
            //                    new CommonUtils().showKeyboard(authoEditText);
            //                }
            //            });
            authoEditText!!.requestFocus()
            authoEditText!!.setSelection(authoEditText!!.text.length)
            authoEditText!!.isFocusableInTouchMode = true
            showKeyboard(authoEditText!!)
        }
    }

    fun hideKeyboard(view: View) {
        val immhide = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        immhide.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }
}