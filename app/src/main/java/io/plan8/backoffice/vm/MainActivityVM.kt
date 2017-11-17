package io.plan8.backoffice.vm

import android.databinding.Bindable
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import io.plan8.backoffice.BR
import io.plan8.backoffice.activity.MainActivity

/**
 * Created by chokwanghwan on 2017. 11. 9..
 */
class MainActivityVM(mainActivity: MainActivity, savedInstanceState: Bundle?) : ActivityVM(mainActivity, savedInstanceState) {
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
        emptyTeamFlag = false
    }
}