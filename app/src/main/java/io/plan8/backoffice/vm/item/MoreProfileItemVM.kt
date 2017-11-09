package io.plan8.backoffice.vm.item

import android.databinding.Bindable
import android.os.Bundle
import android.support.v4.app.Fragment
import io.plan8.backoffice.model.item.MoreProfileItem
import io.plan8.backoffice.vm.FragmentVM

/**
 * Created by SSozi on 2017. 11. 10..
 */
class MoreProfileItemVM(var fragment: Fragment, savedInstanceState: Bundle?, var MoreProfileItem: MoreProfileItem) : FragmentVM(fragment, savedInstanceState) {
    @Bindable
    fun getProfileName(): String {
        return MoreProfileItem.profileName
    }

    @Bindable
    fun getProfilePhoneNumber(): String {
        return MoreProfileItem.profilePhoneNumber
    }
}
