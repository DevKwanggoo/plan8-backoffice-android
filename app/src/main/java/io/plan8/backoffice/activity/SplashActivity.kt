package io.plan8.backoffice.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import io.plan8.backoffice.BR
import io.plan8.backoffice.Constants
import io.plan8.backoffice.R
import io.plan8.backoffice.SharedPreferenceManager
import io.plan8.backoffice.adapter.RestfulAdapter
import io.plan8.backoffice.databinding.ActivitySplashBinding
import io.plan8.backoffice.model.api.Me
import io.plan8.backoffice.vm.SplashActivityVM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    lateinit var vm: SplashActivityVM
    var progressBar: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        vm = SplashActivityVM(this, savedInstanceState)
        binding.setVariable(BR.vm, vm)
        binding.executePendingBindings()

        progressBar = binding.splashProgressBarContainer
        progressBar!!.visibility = View.VISIBLE

        if (SharedPreferenceManager(applicationContext).userToken != "") {
            val token = SharedPreferenceManager(applicationContext).userToken

            if (RestfulAdapter.instance!!.serviceApi != null) {
                RestfulAdapter.instance!!.serviceApi!!.getMe("Bearer " + token).enqueue(object : Callback<Me> {
                    override fun onResponse(call: Call<Me>?, response: Response<Me>?) {
                        if (response?.body() != null) {
                            Constants.me = response.body()!!
                            hasTokenStep()
                        }
                    }

                    override fun onFailure(call: Call<Me>?, t: Throwable?) {
                        Toast.makeText(applicationContext, "잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        } else {
            progressBar!!.visibility = View.GONE
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
            overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity)
        }
    }

    private fun hasTokenStep() {
        progressBar!!.visibility = View.GONE
        startActivity(MainActivity.buildIntent(this))
        finish()
        overridePendingTransition(R.anim.pull_in_right_activity, R.anim.push_out_left_activity)
    }
}
