package io.plan8.backoffice.vm.item

import android.databinding.Bindable
import android.os.Bundle
import android.support.v4.app.Fragment
import io.plan8.backoffice.model.item.MoreTitleItem
import io.plan8.backoffice.vm.FragmentVM

/**
 * Created by SSozi on 2017. 11. 10..
 */
class MoreTitleItemVM(var fragment: Fragment, savedInstanceState: Bundle?, var moreTitleItem: MoreTitleItem) : FragmentVM(fragment, savedInstanceState) {
    @Bindable
    fun getMoreTitle(): String {
        return moreTitleItem.moreTitle
    }
}