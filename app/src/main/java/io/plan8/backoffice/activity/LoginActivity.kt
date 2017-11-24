package io.plan8.backoffice.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crash.FirebaseCrash
import io.plan8.backoffice.BR
import io.plan8.backoffice.Constants
import io.plan8.backoffice.R
import io.plan8.backoffice.SharedPreferenceManager
import io.plan8.backoffice.adapter.RestfulAdapter
import io.plan8.backoffice.databinding.ActivityLoginBinding
import io.plan8.backoffice.model.api.AuthInfo
import io.plan8.backoffice.model.api.LoginInfo
import io.plan8.backoffice.model.api.Me
import io.plan8.backoffice.model.api.Team
import io.plan8.backoffice.vm.LoginActivityVM
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity(), TextView.OnEditorActionListener {

    private lateinit var binding: ActivityLoginBinding
    var vm: LoginActivityVM? = null
    var progressBar: RelativeLayout? = null
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        FirebaseCrash.report(Exception("My first Android non-fatal error"))

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        vm = LoginActivityVM(this, savedInstanceState)
        binding.setVariable(BR.vm, vm)
        binding.executePendingBindings()

        progressBar = binding.loginProgressBarContainer
        binding.loginPhoneNumber.setOnEditorActionListener(this)
        binding.loginNextStep.setOnClickListener { nextStep() }
    }

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
        if (v.id == binding.loginPhoneNumber.id && actionId == EditorInfo.IME_ACTION_DONE) {
            nextStep()
        }
        return false
    }

    private fun nextStep() {
        val phoneNumber = binding.loginPhoneNumber.text.toString()

        if (phoneNumber != "" && phoneNumber.length > 9) {
            if (RestfulAdapter.instance!!.serviceApi != null) {
                RestfulAdapter.instance!!.serviceApi!!.getPinCode(phoneNumber).enqueue(object : Callback<LoginInfo> {
                    override fun onFailure(call: Call<LoginInfo>?, t: Throwable?) {
                        Toast.makeText(applicationContext, "잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<LoginInfo>?, response: Response<LoginInfo>?) {
                        if (response != null) {
                            nextActivity(phoneNumber, response.body()!!.code)
                        }
                    }

                })
            } else {
                Toast.makeText(this, "휴대전화번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun nextActivity(phoneNumber: String, code: String) {
        val intent = Intent(this, LoginAuthorizationActivity::class.java)
        intent.putExtra("phoneNumber", phoneNumber)
        intent.putExtra("code", code)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity)
    }
}
