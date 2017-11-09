package io.plan8.backoffice.vm.item

import android.databinding.Bindable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import io.plan8.backoffice.BR
import io.plan8.backoffice.model.item.MoreTeamItem
import io.plan8.backoffice.vm.FragmentVM

/**
 * Created by SSozi on 2017. 11. 10..
 */
class MoreTeamItemVM(var fragment: Fragment, savedInstanceState: Bundle?, var MoreTeamItem: MoreTeamItem) : FragmentVM(fragment, savedInstanceState) {
    private var clickFlag = false

    @Bindable
    fun getTeamName(): String {
        return MoreTeamItem.moreTeamName
    }

    @Bindable
    fun getTeamDescription(): String {
        return MoreTeamItem.moreTeamDescription
    }

    @Bindable
    fun getSelectTeamFlag(): Boolean {
        return clickFlag
    }

    fun setSelecTeamFlag(flag: Boolean){
        clickFlag = flag
        notifyPropertyChanged(BR.selectTeamFlag)
    }

    fun selectTeam(view: View){
        setSelecTeamFlag(!clickFlag)
    }
}