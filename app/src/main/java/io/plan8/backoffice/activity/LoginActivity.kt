package io.plan8.backoffice.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import io.plan8.backoffice.BR
import io.plan8.backoffice.R
import io.plan8.backoffice.databinding.ActivityLoginBinding
import io.plan8.backoffice.vm.LoginActivityVM

class LoginActivity : BaseActivity(), TextView.OnEditorActionListener {

    private lateinit var binding: ActivityLoginBinding
    var vm: LoginActivityVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        vm = LoginActivityVM(this, savedInstanceState)
        binding.setVariable(BR.vm, vm)
        binding.executePendingBindings()

        binding.loginNextStep.setOnClickListener({ nextStep() })
        binding.loginPhoneNumber.setOnEditorActionListener(this)
        binding.deleteNumberBtn.setOnClickListener { binding.loginPhoneNumber.setText("") }
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
            val intent = Intent(this, LoginAuthorizationActivity::class.java)
            intent.putExtra("phoneNumber", phoneNumber)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity)
        } else {
            Toast.makeText(this, "휴대전화번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}
