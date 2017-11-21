package io.plan8.backoffice.vm

import android.content.Intent
import android.databinding.Bindable
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import io.plan8.backoffice.BR
import io.plan8.backoffice.Constants
import io.plan8.backoffice.R
import io.plan8.backoffice.activity.LoginActivity
import io.plan8.backoffice.activity.MainActivity

/**
 * Created by chokwanghwan on 2017. 11. 9..
 */
class MainActivityVM(var mainActivity: MainActivity, savedInstanceState: Bundle?) : ActivityVM(mainActivity, savedInstanceState) {
    var emptyTeamFlag = true
        @Bindable
        get() {
            return field
        }
        set(value) {
            field = value
            notifyPropertyChanged(BR.emptyTeamFlag)
        }

    fun teamLogout(view: View){
//        val intent = Intent(mainActivity, LoginActivity::class.java)
//        Constants.me = null
//        Constants.team = null
//        mainActivity.startActivity(intent)
//        mainActivity.finish()
//        mainActivity.overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity)
        emptyTeamFlag = false
    }
}